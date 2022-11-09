package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.PersonsBasicInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonBasicInfoRepository extends PagingAndSortingRepository<PersonsBasicInfo,Integer> {
}
