package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.AuthenticationDTO;
import com.sts.tradeunion.dto.UserDTO;
import com.sts.tradeunion.entities.UserEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.exceptions.PersonNotFoundException;
import com.sts.tradeunion.security.jwt.JWTUtil;
import com.sts.tradeunion.services.UserServiceImpl;
import com.sts.tradeunion.util.validation.AuthenticationDTOValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final AuthenticationDTOValidator validator;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserController(AuthenticationDTOValidator validator, UserServiceImpl userService, ModelMapper modelMapper, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.validator = validator;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> performRegistration(@RequestBody @Valid AuthenticationDTO user, BindingResult bindingResult) {
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            throw new EntityIsNotValidException(bindingResult, user);
        return new ResponseEntity<>(modelMapper
                .map(userService.register(modelMapper.map(user, UserEntity.class)), UserDTO.class), HttpStatus.OK);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO user) {
        UserDetails userDetails;
        UsernamePasswordAuthenticationToken authenticationInputToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        try {
            authenticationManager.authenticate(authenticationInputToken);
            userDetails = userService.loadUserByUsername(user.getUsername());
        } catch (BadCredentialsException e) {
            return Map.of("message", "Неверные данные пользователя");
        }
        String token = jwtUtil.generateToken(user.getUsername(),
                userDetails.getAuthorities().stream().findFirst().orElseThrow().getAuthority());
        System.out.println("LOGIN SUCCESS");
        return Map.of("jwt-token", token);
    }

}
