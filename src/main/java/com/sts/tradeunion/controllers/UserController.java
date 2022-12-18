package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.AuthenticationDTO;
import com.sts.tradeunion.dto.RegistrationDTO;
import com.sts.tradeunion.dto.UserDTO;
import com.sts.tradeunion.entities.UserEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.exceptions.SuchUserHaveAlreadyExisted;
import com.sts.tradeunion.security.jwt.JWTUtil;
import com.sts.tradeunion.services.UserServiceImpl;
import com.sts.tradeunion.util.validation.RegistrationDTOValidator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final RegistrationDTOValidator validator;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public UserController(RegistrationDTOValidator validator, UserServiceImpl userService, ModelMapper modelMapper,
                          JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.validator = validator;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> performRegistration(@RequestBody @Valid RegistrationDTO user, BindingResult bindingResult) {
        logger.info("Регистрация пользователя {}", user.getUsername());
        if (userService.findByUsername(user.getUsername()).isPresent()){
            throw new SuchUserHaveAlreadyExisted(user.getUsername());
        }
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors()){
            throw new EntityIsNotValidException(bindingResult, user);
        }
        logger.info("Регистрация пользователя {} произведена успешно", user.getUsername());
        return new ResponseEntity<>(modelMapper
                .map(userService.register(modelMapper.map(user, UserEntity.class)), UserDTO.class), HttpStatus.OK);
    }

    @PostMapping("/login")
    public Map<String,Object> performLogin(@RequestBody AuthenticationDTO user) {
        UserDetails userDetails;
        UsernamePasswordAuthenticationToken authenticationInputToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        try {
            logger.info("Аутентификация пользователя {}", user.getUsername());
            authenticationManager.authenticate(authenticationInputToken);
            userDetails = userService.loadUserByUsername(user.getUsername());
        } catch (BadCredentialsException e) {
            logger.info("Пользователь {} ввел некорректные данные", user.getUsername());
            return Map.of("message", "Неверные данные пользователя");
        }
        String token = jwtUtil.generateToken(user.getUsername(),
                userDetails.getAuthorities().stream().findFirst().orElseThrow().getAuthority());
        logger.info("Аутентификация пользователя {} произведена успешно", userDetails.getUsername());

        return Map.of(
                "user",
                modelMapper.map(userService.findByUsername(userDetails.getUsername()).get(),UserDTO.class),
                "jwt_token",
                "Bearer " + token);
    }

}
