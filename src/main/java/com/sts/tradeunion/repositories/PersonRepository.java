package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<PersonEntity,Integer>{
    public Page<PersonEntity> findByLastName(String lastName, Pageable pageable);
}
