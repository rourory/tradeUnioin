package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.LaborContractEntity;
import com.sts.tradeunion.exceptions.EntityNotFoundException;
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
        laborContract.setOwner(personRepository.findById(ownerId).orElseThrow(() -> new EntityNotFoundException(ownerId)));
        laborContract.setUpdated(LocalDateTime.now());
        laborContract.setCreated(new Date());
        return laborContractRepository.save(laborContract);
    }

    @Transactional
    public LaborContractEntity update(LaborContractEntity laborContract, int ownerId) {
        laborContract.setOwner(personRepository.findById(ownerId).orElseThrow(() -> new EntityNotFoundException(ownerId)));
        laborContract.setUpdated(LocalDateTime.now());
        laborContract.setCreated(laborContractRepository.getCreatedDateById(laborContract.getId()));
        return laborContractRepository.save(laborContract);
    }

    @Transactional
    public boolean delete(int id) {
        laborContractRepository.delete(findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
        return findById(id).isPresent();
    }

    public List<LaborContractEntity> findByOwnerId(int ownerId) {
        return laborContractRepository.getLaborContractsByOwner(ownerId);
    }


}
