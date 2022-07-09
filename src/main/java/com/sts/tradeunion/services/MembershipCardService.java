package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.docs.MembershipCardEntity;
import com.sts.tradeunion.repositories.MembershipCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipCardService {

    private final MembershipCardRepository membershipCardRepository;

    @Autowired
    public MembershipCardService(MembershipCardRepository membershipCardRepository) {
        this.membershipCardRepository = membershipCardRepository;
    }

    public MembershipCardEntity getMembershipCardByOwnerId(int id){
        PersonEntity person = new PersonEntity();
        person.setId(id);
        return membershipCardRepository.getMembershipCardEntityByOwner(person);
    }
}
