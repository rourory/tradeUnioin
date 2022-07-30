package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.docs.LaborContractEntity;
import com.sts.tradeunion.exceptions.PersonNotFoundException;
import com.sts.tradeunion.repositories.LaborContractRepository;
import com.sts.tradeunion.repositories.PersonRepository;
import com.sts.tradeunion.services.interfaces.WithOwnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LaborContractServiceImpl implements WithOwnerService<LaborContractEntity> {

    private final LaborContractRepository laborContractRepository;
    private final PersonRepository personRepository;

    public LaborContractServiceImpl(LaborContractRepository laborContractRepository, PersonRepository personRepository) {
        this.laborContractRepository = laborContractRepository;
        this.personRepository = personRepository;
    }

    public Optional<LaborContractEntity> findById(int id) {
        return laborContractRepository.findById(id);
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
    public boolean delete(PersonEntity owner, int id) {
        return laborContractRepository.deleteByOwnerAndId(personRepository.findById(owner.getId()).orElseThrow(PersonNotFoundException::new),id);
    }

    public List<LaborContractEntity> findByOwnerId(int ownerId) {
        return laborContractRepository.getLaborContractsByOwner(ownerId);
    }


}
