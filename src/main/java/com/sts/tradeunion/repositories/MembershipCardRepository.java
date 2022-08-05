package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.MembershipCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipCardRepository extends JpaRepository<MembershipCardEntity, Integer> {
   @Query(value = "SELECT * FROM doc_member AS dm WHERE dm.person_id = ?1", nativeQuery = true)
   List<MembershipCardEntity> getMembershipCardsByOwner(int ownerId);
   @Query(value = "SELECT created FROM doc_member AS dm WHERE dm.id = ?1", nativeQuery = true)
   Date getCreatedDateById(int id);
   Optional<MembershipCardEntity>findMembershipCardEntityByCardNumber(int cardNumber);

   boolean deleteByOwnerAndId(PersonEntity owner, int id);
}
