package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.docs.MembershipCardEntity;
import com.sts.tradeunion.repositories.MembershipCardRepository;
import com.sts.tradeunion.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MembershipCardService {

    private final MembershipCardRepository membershipCardRepository;
    private final PersonRepository personRepository;

    @Autowired
    public MembershipCardService(MembershipCardRepository membershipCardRepository, PersonRepository personRepository) {
        this.membershipCardRepository = membershipCardRepository;
        this.personRepository = personRepository;
    }

    public List<MembershipCardEntity> findByOwnerId(int ownerId) {
        List<MembershipCardEntity> m = membershipCardRepository.getMembershipCardEntityByOwner(ownerId);
        return membershipCardRepository.getMembershipCardEntityByOwner(ownerId);
    }

    @Transactional
    public MembershipCardEntity save(MembershipCardEntity membershipCard, int ownerId) {
        membershipCard.setOwner(personRepository.findById(ownerId).get());
        membershipCard.setUpdated(LocalDateTime.now());
        membershipCard.setCreated(new Date());
        return membershipCardRepository.save(membershipCard);
    }

    @Transactional
    public MembershipCardEntity update(MembershipCardEntity membershipCard, int ownerId) {
        membershipCard.setOwner(personRepository.findById(ownerId).get());
        membershipCard.setUpdated(LocalDateTime.now());
        membershipCard.setCreated(membershipCardRepository.getCreatedDateById(membershipCard.getId()));
        return membershipCardRepository.save(membershipCard);
    }

    @Transactional
    public void delete(int ownerId, int id) {
        MembershipCardEntity membershipCard = new MembershipCardEntity();
        membershipCard.setId(id);
        PersonEntity owner = new PersonEntity();
        owner.setId(ownerId);
        membershipCard.setOwner(owner);
        membershipCardRepository.delete(membershipCard);
    }


}
