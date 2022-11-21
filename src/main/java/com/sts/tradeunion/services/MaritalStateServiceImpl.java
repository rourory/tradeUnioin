package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.MaritalStateEntity;
import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.repositories.MaritalStateRepository;
import com.sts.tradeunion.services.interfaces.WithoutOwnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MaritalStateServiceImpl implements WithoutOwnerService<MaritalStateEntity> {

    private final MaritalStateRepository maritalStateRepository;

    public MaritalStateServiceImpl(MaritalStateRepository maritalStateRepository) {
        this.maritalStateRepository = maritalStateRepository;
    }

    public List<MaritalStateEntity> getAll() {
        List<MaritalStateEntity> states = new ArrayList<>();
        maritalStateRepository.findAll().forEach(states::add);
        return states;
    }

    @Override
    public Optional<MaritalStateEntity> findById(int id) {
        return maritalStateRepository.findById(id);
    }
    @Transactional
    @Override
    public MaritalStateEntity save(MaritalStateEntity entity) {
        entity.setUpdated(LocalDateTime.now());
        return maritalStateRepository.save(entity);
    }
    @Transactional
    @Override
    public boolean deleteById(int id) {
        return maritalStateRepository.deleteById(id);
    }
    @Transactional
    @Override
    public MaritalStateEntity update(MaritalStateEntity entity) {
        entity.setUpdated(LocalDateTime.now());
        return maritalStateRepository.save(entity);
    }
}
