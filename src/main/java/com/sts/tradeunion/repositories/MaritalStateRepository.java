package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.EducationClassificationEntity;
import com.sts.tradeunion.entities.MaritalStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaritalStateRepository extends JpaRepository<MaritalStateEntity,Integer> {
    Optional<MaritalStateEntity> findByName(String name);
    boolean deleteById(int id);
}
