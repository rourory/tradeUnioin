package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.сlassification.EducationClassificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<EducationClassificationEntity,Integer> {
    Optional<EducationClassificationEntity> findByName(String name);
}
