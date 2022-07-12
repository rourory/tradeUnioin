package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.exceptions.PersonNotFoundException;
import com.sts.tradeunion.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonEntity> getAllPeople(int page){
        return personRepository.findAll(PageRequest.of(page,3,Sort.by("lastName").ascending())).getContent();
    }
    public PersonEntity getPerson (int id){
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }
    @Transactional
    public PersonEntity save(PersonEntity person){
        person.setUpdated(LocalDateTime.now());
        return personRepository.save(person);
    }

    @Transactional
    public void deletePerson(int id){
        personRepository.delete(personRepository.findById(id).get());
    }
    public boolean isExists(PersonEntity person){
        return personRepository.existsById(person.getId());
    }
    public List<PersonEntity> findByLastName (String lastName, int page){
        return personRepository.findByLastName(lastName, PageRequest.of(page,3,Sort.by("lastName").ascending())).getContent();
    }

}
