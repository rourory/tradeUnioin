package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.PersonsBasicInfo;
import com.sts.tradeunion.repositories.PersonBasicInfoRepository;
import com.sts.tradeunion.repositories.PersonRepository;
import com.sts.tradeunion.services.interfaces.WithoutOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements WithoutOwnerService<PersonEntity> {

    private final PersonRepository personRepository;
    private final PersonBasicInfoRepository personBasicInfoRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PersonBasicInfoRepository personBasicInfoRepository) {
        this.personRepository = personRepository;
        this.personBasicInfoRepository = personBasicInfoRepository;
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
        personRepository.deleteById(id);
        return personRepository.findById(id).isEmpty();
    }

    public List<PersonEntity> getAll() {
        List<PersonEntity> people = new ArrayList<>();
        personRepository.findAll().forEach(people::add);
        return people;
    }

    public List<PersonsBasicInfo> getBasicInfoOfAllPeople() {
        List<PersonsBasicInfo> people = new ArrayList<>();
       personBasicInfoRepository.findAll().forEach(people::add);
        return people;
    }
    public boolean isExist(int personId) {
        return personRepository.existsById(personId);
    }

}
