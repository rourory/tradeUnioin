package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.docs.LaborContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LaborContractRepository extends JpaRepository<LaborContractEntity,Integer> {

    @Query(value = "SELECT * FROM doc_job AS dj WHERE dj.person_id = ?1", nativeQuery = true)
    List<LaborContractEntity> getLaborContractsByOwner(int ownerId);

    @Query(value = "SELECT created FROM doc_job AS dj WHERE dj.id = ?1", nativeQuery = true)
    Date getCreatedDateById(int id);
}
