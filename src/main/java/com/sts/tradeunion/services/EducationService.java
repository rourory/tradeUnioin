package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.сlassification.EducationClassificationEntity;
import com.sts.tradeunion.repositories.EducationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EducationService {

    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public Optional<EducationClassificationEntity> findByName(String name){
        return educationRepository.findByName(name);
    }

    @Transactional
    public EducationClassificationEntity save(EducationClassificationEntity education, int ownerId) {
        education.setUpdated(LocalDateTime.now());
        return educationRepository.save(education);
    }

    @Transactional
    public EducationClassificationEntity update(EducationClassificationEntity education, int ownerId) {
        education.setUpdated(LocalDateTime.now());
        return educationRepository.save(education);
    }

    @Transactional
    public void delete(int id) {
        EducationClassificationEntity education = new EducationClassificationEntity();
        education.setId(id);
        educationRepository.delete(education);
    }
}
