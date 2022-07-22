package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.UserDTO;
import com.sts.tradeunion.entities.UserEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.services.UserService;
import com.sts.tradeunion.util.validation.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserValidator userValidator;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserValidator userValidator, UserService userService, ModelMapper modelMapper) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> loginPage() {
        return new ResponseEntity<>(new UserDTO(), HttpStatus.OK);
    }

    @GetMapping("/registration")
    public ResponseEntity<UserDTO> registrationPage() {
        return new ResponseEntity<>(new UserDTO(), HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> performRegistration(@RequestBody @Valid UserDTO user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            throw new EntityIsNotValidException(bindingResult, user);
        return new ResponseEntity<>(modelMapper
                .map(userService.save(modelMapper.map(user, UserEntity.class)),UserDTO.class),HttpStatus.OK);

    }
}
