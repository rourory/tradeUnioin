package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.PersonDTO;
import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.services.PersonService;

import com.sts.tradeunion.util.validation.PersonValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController{

    private final PersonService personService;
    private final ModelMapper modelMapper;
    private final PersonValidator personValidator;

    public PeopleController(PersonService personService, ModelMapper modelMapper, PersonValidator personValidator) {
        this.personService = personService;
        this.modelMapper = modelMapper;
        this.personValidator = personValidator;
    }

    //Get list with all person
    @GetMapping
    public ResponseEntity<List<PersonDTO>> getPeople(@RequestParam("page") int page) {
        List<PersonDTO> people = new ArrayList<>();
        if (page > 0)
            personService.getAllPeople(page - 1).forEach(person -> people.add(modelMapper.map(person, PersonDTO.class)));
        return new ResponseEntity<>(people,HttpStatus.OK);
    }

    //Get person with definite id
    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable int id) {
        return modelMapper.map(personService.getPerson(id), PersonDTO.class);
    }

    //Create new person
    @PostMapping
    public ResponseEntity<Object> createPerson(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        personValidator.validate(personDTO,bindingResult);
        if(bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, personDTO);
        return new ResponseEntity<>(modelMapper.map(personService
                .save(modelMapper.map(personDTO, PersonEntity.class)), PersonDTO.class), HttpStatus.OK);
    }

    //Update definite person
    @PutMapping
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        personValidator.validate(personDTO,bindingResult);
        if(bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, personDTO);
        return new ResponseEntity<>(modelMapper.map(personService
                .save(modelMapper.map(personDTO, PersonEntity.class)), PersonDTO.class), HttpStatus.OK);
    }

    //Delete definite person
    @DeleteMapping
    public ResponseEntity<HttpStatus> deletePerson(@RequestParam("id") int id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

