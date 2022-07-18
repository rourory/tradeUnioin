package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.сlassification.TradeUnionClassificationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TradeUnionRepository extends PagingAndSortingRepository<TradeUnionClassificationEntity,Integer> {
    Optional<TradeUnionClassificationEntity>findById(int id);

    boolean deleteById(int id);
}
