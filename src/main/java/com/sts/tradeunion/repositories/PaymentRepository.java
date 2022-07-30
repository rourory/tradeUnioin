package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.docs.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {

    @Query(value = "SELECT * FROM doc_payment AS dp WHERE dp.person_id = ?1", nativeQuery = true)
    List<PaymentEntity> getPaymentsByOwnerId(int ownerId);

    @Query(value = "SELECT created FROM doc_payment AS dp WHERE dp.id = ?1", nativeQuery = true)
    Date getCreatedDateById(int id);

    boolean deleteByOwnerAndId(PersonEntity owner, int id);
}
