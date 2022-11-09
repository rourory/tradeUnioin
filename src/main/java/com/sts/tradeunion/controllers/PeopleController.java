package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.PersonDTO;
import com.sts.tradeunion.dto.ResponseEntityDTO;
import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.exceptions.PersonNotFoundException;
import com.sts.tradeunion.services.PersonServiceImpl;
import com.sts.tradeunion.util.validation.PersonValidator;
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
@RequestMapping("/people")
public class PeopleController {

    private final PersonServiceImpl personService;
    private final ModelMapper modelMapper;
    private final PersonValidator personValidator;

    public PeopleController(PersonServiceImpl personService, ModelMapper modelMapper, PersonValidator personValidator) {
        this.personService = personService;
        this.modelMapper = modelMapper;
        this.personValidator = personValidator;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<List<PersonDTO>> getBasicInfo() {
        List<PersonDTO> people = new ArrayList<>();
            personService.getBasicInfoOfAllPeople().forEach(person -> people.add(modelMapper.map(person, PersonDTO.class)));
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public PersonDTO get(@PathVariable int id) {
        return modelMapper.map(personService
                .findById(id).orElseThrow(() -> new PersonNotFoundException(id)),PersonDTO.class);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<Object> save(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        personValidator.validate(personDTO, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, personDTO);
        return new ResponseEntity<>(modelMapper.map(personService
                .save(modelMapper.map(personDTO, PersonEntity.class)), PersonDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<PersonDTO> update(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        personValidator.validate(personDTO, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, personDTO);
        return new ResponseEntity<>(modelMapper.map(personService
                .save(modelMapper.map(personDTO, PersonEntity.class)), PersonDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<HttpStatus> delete(@RequestParam("id") int id) {
        if (personService.deleteById(id))
            personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

