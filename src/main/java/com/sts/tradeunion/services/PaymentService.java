package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.docs.PaymentEntity;
import com.sts.tradeunion.repositories.PaymentRepository;
import com.sts.tradeunion.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PersonRepository personRepository;

    public PaymentService(PaymentRepository paymentRepository, PersonRepository personRepository) {
        this.paymentRepository = paymentRepository;
        this.personRepository = personRepository;
    }

    public List<PaymentEntity> findByOwnerId(int ownerId) {
        return paymentRepository.getPaymentsByOwnerId(ownerId);
    }

    @Transactional
    public PaymentEntity save(PaymentEntity payment, int ownerId) {
        payment.setOwner(personRepository.findById(ownerId).get());
        payment.setUpdated(LocalDateTime.now());
        payment.setCreated(new Date());
        return paymentRepository.save(payment);
    }

    @Transactional
    public PaymentEntity update(PaymentEntity payment, int ownerId) {
        payment.setOwner(personRepository.findById(ownerId).get());
        payment.setUpdated(LocalDateTime.now());
        payment.setCreated(paymentRepository.getCreatedDateById(payment.getId()));
        return paymentRepository.save(payment);
    }

    @Transactional
    public void delete(int ownerId, int id) {
        PaymentEntity payment = new PaymentEntity();
        payment.setId(id);
        PersonEntity owner = new PersonEntity();
        owner.setId(ownerId);
        payment.setOwner(owner);
        paymentRepository.delete(payment);
    }
}
