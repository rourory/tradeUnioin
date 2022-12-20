package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.UserDTO;
import com.sts.tradeunion.entities.UserEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.exceptions.EntityNotFoundException;
import com.sts.tradeunion.services.UserServiceImpl;
import com.sts.tradeunion.util.validation.UserValidator;
import io.swagger.annotations.ApiImplicitParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UsersController {
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;
    private final UserValidator validator;

    public UsersController(UserServiceImpl userService, ModelMapper modelMapper, UserValidator validator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<List<UserEntity>> getUsers() {
        List<UserEntity> users = new ArrayList<>(userService.getAll());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public UserEntity get(@PathVariable int id) {
        return userService.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<UserEntity> update(@Valid @RequestBody UserEntity user, BindingResult bindingResult) {
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, modelMapper.map(user, UserDTO.class));
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<Object> delete(@RequestParam("id") int id) {
        userService.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }
}
