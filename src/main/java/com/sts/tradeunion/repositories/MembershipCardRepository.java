package com.sts.tradeunion.repositories.membershipCard;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.docs.MembershipCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipCardRepository extends JpaRepository<MembershipCardEntity,Integer> {
    public MembershipCardEntity getMembershipCardEntityByOwner(PersonEntity owner);
}
