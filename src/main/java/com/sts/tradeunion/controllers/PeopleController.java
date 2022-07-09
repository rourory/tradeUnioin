package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.PersonDTO;
import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    //Get list with all person
    @GetMapping
    public List<PersonDTO> getPeople(@RequestParam("page") int page) {
        List<PersonDTO> people = new ArrayList<>();
        if (page > 0)
            personService.getAllPeople(page - 1).stream().forEach(person -> people.add(modelMapper.map(person, PersonDTO.class)));
        return people;
    }

    //Get person with definite id
    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable int id) {
        return modelMapper.map(personService.getPerson(id), PersonDTO.class);
    }

    //Create new person
    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(modelMapper.map(personService.save(modelMapper.map(personDTO, PersonEntity.class)), PersonDTO.class), HttpStatus.OK);
    }

    //Update definite person
    @PutMapping
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(modelMapper.map(personService.save(modelMapper.map(personDTO, PersonEntity.class)), PersonDTO.class), HttpStatus.OK);
    }

    //Delete definite person
    @DeleteMapping
    public ResponseEntity<HttpStatus> deletePerson(@RequestParam("id") int id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

