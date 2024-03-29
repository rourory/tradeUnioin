package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.TradeUnionClassificationEntity;
import com.sts.tradeunion.exceptions.EntityNotFoundException;
import com.sts.tradeunion.repositories.TradeUnionRepository;
import com.sts.tradeunion.services.interfaces.WithoutOwnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TradeUnionServiceImpl implements WithoutOwnerService<TradeUnionClassificationEntity> {

    private final TradeUnionRepository tradeUnionRepository;

    public TradeUnionServiceImpl(TradeUnionRepository tradeUnionRepository) {
        this.tradeUnionRepository = tradeUnionRepository;
    }

    public List<TradeUnionClassificationEntity> getAll(){
        List<TradeUnionClassificationEntity> orgs = new ArrayList<>();
        tradeUnionRepository.findAll().forEach(tradeUnionClassificationEntity -> orgs.add(tradeUnionClassificationEntity));
        return orgs;
    }

    public Optional<TradeUnionClassificationEntity> findById(int id) {
        return tradeUnionRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(int id) {
        tradeUnionRepository.delete(findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
        return tradeUnionRepository.findById(id).isPresent();
    }

    @Transactional
    public TradeUnionClassificationEntity save(TradeUnionClassificationEntity tradeUnion) {
        tradeUnion.setUpdated(LocalDateTime.now());
        return tradeUnionRepository.save(tradeUnion);
    }

    @Transactional
    public TradeUnionClassificationEntity update(TradeUnionClassificationEntity tradeUnion) {
        tradeUnion.setUpdated(LocalDateTime.now());
        return tradeUnionRepository.save(tradeUnion);
    }

    public boolean isExists(int id) {
        return tradeUnionRepository.existsById(id);
    }
}
