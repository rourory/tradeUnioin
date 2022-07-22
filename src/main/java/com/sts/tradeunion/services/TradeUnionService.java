package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.сlassification.TradeUnionClassificationEntity;
import com.sts.tradeunion.repositories.TradeUnionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TradeUnionService {

    private final TradeUnionRepository tradeUnionRepository;

    public TradeUnionService(TradeUnionRepository tradeUnionRepository) {
        this.tradeUnionRepository = tradeUnionRepository;
    }

    public Optional<TradeUnionClassificationEntity> findById(int id) {
        return tradeUnionRepository.findById(id);
    }

    @Transactional
    public TradeUnionClassificationEntity save(TradeUnionClassificationEntity tradeUnion) {
        tradeUnion.setUpdated(LocalDateTime.now());
        return tradeUnionRepository.save(tradeUnion);
    }

    @Transactional
    public TradeUnionClassificationEntity update(TradeUnionClassificationEntity tradeUnion, int ownerId) {
        tradeUnion.setUpdated(LocalDateTime.now());
        return tradeUnionRepository.save(tradeUnion);
    }

    @Transactional
    public boolean delete(int id) {
        return tradeUnionRepository.deleteById(id);
    }

    public boolean isExists(int id) {
        return tradeUnionRepository.existsById(id);
    }
}
