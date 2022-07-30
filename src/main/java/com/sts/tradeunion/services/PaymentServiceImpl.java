package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.docs.PaymentEntity;
import com.sts.tradeunion.exceptions.PersonNotFoundException;
import com.sts.tradeunion.repositories.PaymentRepository;
import com.sts.tradeunion.repositories.PersonRepository;
import com.sts.tradeunion.services.interfaces.WithOwnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaymentServiceImpl implements WithOwnerService<PaymentEntity> {

    private final PaymentRepository paymentRepository;

    private final PersonRepository personRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PersonRepository personRepository) {
        this.paymentRepository = paymentRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Optional<PaymentEntity> findById(int id) {
        return paymentRepository.findById(id);
    }

    @Transactional
    public PaymentEntity save(PaymentEntity payment, int ownerId) {
        payment.setOwner(personRepository.findById(ownerId).orElseThrow(PersonNotFoundException::new));
        payment.setUpdated(LocalDateTime.now());
        payment.setCreated(new Date());
        return paymentRepository.save(payment);
    }

    @Transactional
    public PaymentEntity update(PaymentEntity payment, int ownerId) {
        payment.setOwner(personRepository.findById(ownerId).orElseThrow(PersonNotFoundException::new));
        payment.setUpdated(LocalDateTime.now());
        payment.setCreated(paymentRepository.getCreatedDateById(payment.getId()));
        return paymentRepository.save(payment);
    }

    @Transactional
    public boolean delete(int ownerId, int id) {
        return paymentRepository.deleteByOwnerAndId(personRepository.findById(ownerId).orElseThrow(PersonNotFoundException::new), id);
    }

    public List<PaymentEntity> findByOwnerId(int ownerId) {
        return paymentRepository.getPaymentsByOwnerId(ownerId);
    }
}
