package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.exceptions.PersonNotFoundException;
import com.sts.tradeunion.repositories.PersonRepository;
import com.sts.tradeunion.services.interfaces.WithoutOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements WithoutOwnerService<PersonEntity> {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<PersonEntity> findById(int id) {
        return personRepository.findById(id);
    }
    @Transactional
    public PersonEntity save(PersonEntity person) {
        person.setUpdated(LocalDateTime.now());
        return personRepository.save(person);
    }
    @Transactional
    public PersonEntity update(PersonEntity person) {
        person.setUpdated(LocalDateTime.now());
        return personRepository.save(person);
    }
    @Transactional
    public boolean deleteById(int id) {
        return personRepository.deleteById(id);
    }
    public List<PersonEntity> getAll(int page) {
        return personRepository.findAll(PageRequest.of(page, 3, Sort.by("lastName").ascending())).getContent();
    }
    public boolean isExist(int personId) {
        return personRepository.existsById(personId);
    }

}
