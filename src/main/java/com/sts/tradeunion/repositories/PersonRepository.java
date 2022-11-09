package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.PersonsBasicInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends PagingAndSortingRepository<PersonEntity,Integer>{
    boolean deleteById(int id);

}
