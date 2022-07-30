package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.сlassification.EducationClassificationEntity;
import com.sts.tradeunion.repositories.EducationRepository;
import com.sts.tradeunion.services.interfaces.WithoutOwnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EducationServiceImpl implements WithoutOwnerService<EducationClassificationEntity> {

    private final EducationRepository educationRepository;

    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public Optional<EducationClassificationEntity> findById(int id) {
        return educationRepository.findById(id);
    }

    @Transactional
    public EducationClassificationEntity save(EducationClassificationEntity education) {
        education.setUpdated(LocalDateTime.now());
        return educationRepository.save(education);
    }

    @Transactional
    public EducationClassificationEntity update(EducationClassificationEntity education) {
        education.setUpdated(LocalDateTime.now());
        return educationRepository.save(education);
    }

    @Transactional
    public boolean deleteById(int id) {
        return educationRepository.deleteById(id);
    }

    public Optional<EducationClassificationEntity> findByName(String name) {
        return educationRepository.findByName(name);
    }
}
