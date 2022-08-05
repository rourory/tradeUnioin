package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.MembershipCardEntity;
import com.sts.tradeunion.exceptions.PersonNotFoundException;
import com.sts.tradeunion.repositories.MembershipCardRepository;
import com.sts.tradeunion.repositories.PersonRepository;
import com.sts.tradeunion.services.interfaces.WithOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MembershipCardServiceImpl implements WithOwnerService<MembershipCardEntity> {

    private final MembershipCardRepository membershipCardRepository;
    private final PersonRepository personRepository;

    @Autowired
    public MembershipCardServiceImpl(MembershipCardRepository membershipCardRepository, PersonRepository personRepository) {
        this.membershipCardRepository = membershipCardRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public MembershipCardEntity save(MembershipCardEntity membershipCard, int ownerId) {
        membershipCard.setOwner(personRepository.findById(ownerId).orElseThrow(() -> new PersonNotFoundException(ownerId)));
        membershipCard.setUpdated(LocalDateTime.now());
        membershipCard.setCreated(new Date());
        return membershipCardRepository.save(membershipCard);
    }

    @Transactional
    public MembershipCardEntity update(MembershipCardEntity membershipCard, int ownerId) {
        membershipCard.setOwner(personRepository.findById(ownerId).orElseThrow(() -> new PersonNotFoundException(ownerId)));
        membershipCard.setUpdated(LocalDateTime.now());
        membershipCard.setCreated(membershipCardRepository.getCreatedDateById(membershipCard.getId()));
        return membershipCardRepository.save(membershipCard);
    }
    @Transactional
    public boolean delete(int ownerId, int id) {
        return membershipCardRepository.deleteByOwnerAndId(personRepository.findById(ownerId).orElseThrow(() -> new PersonNotFoundException(ownerId)),id);
    }

    public List<MembershipCardEntity> findByOwnerId(int ownerId) {
        return membershipCardRepository.getMembershipCardsByOwner(ownerId);
    }

    public Optional<MembershipCardEntity> findById(int id) {
        return membershipCardRepository.findById(id);
    }

    public Optional<MembershipCardEntity> findByCardNumber(int cardNumber) {
        return membershipCardRepository.findMembershipCardEntityByCardNumber(cardNumber);
    }
}
