package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.PersonEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<PersonEntity,Integer>{
}
