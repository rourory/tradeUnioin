package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.docs.LaborContractEntity;
import com.sts.tradeunion.repositories.LaborContractRepository;
import com.sts.tradeunion.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LaborContractService {

    private final LaborContractRepository laborContractRepository;
    private final PersonRepository personRepository;

    public LaborContractService(LaborContractRepository laborContractRepository, PersonRepository personRepository) {
        this.laborContractRepository = laborContractRepository;
        this.personRepository = personRepository;
    }

    public List<LaborContractEntity> findByOwnerId(int ownerId) {
        return laborContractRepository.getLaborContractsByOwner(ownerId);
    }

    @Transactional
    public LaborContractEntity save(LaborContractEntity laborContract, int ownerId) {
        laborContract.setOwner(personRepository.findById(ownerId).get());
        laborContract.setUpdated(LocalDateTime.now());
        laborContract.setCreated(new Date());
        return laborContractRepository.save(laborContract);
    }

    @Transactional
    public LaborContractEntity update(LaborContractEntity laborContract, int ownerId) {
        laborContract.setOwner(personRepository.findById(ownerId).get());
        laborContract.setUpdated(LocalDateTime.now());
        laborContract.setCreated(laborContractRepository.getCreatedDateById(laborContract.getId()));
        return laborContractRepository.save(laborContract);
    }

    @Transactional
    public void delete(int ownerId, int id) {
        LaborContractEntity laborContract = new LaborContractEntity();
        laborContract.setId(id);
        PersonEntity owner = new PersonEntity();
        owner.setId(ownerId);
        laborContract.setOwner(owner);
        laborContractRepository.delete(laborContract);
    }



}
