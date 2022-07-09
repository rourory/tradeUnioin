package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.docs.MembershipCardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipCardRepository extends JpaRepository<MembershipCardEntity, Integer> {
   @Query(value = "SELECT * FROM doc_member AS dm WHERE dm.person_id = ?1", nativeQuery = true)
   List<MembershipCardEntity> getMembershipCardEntityByOwner(int ownerId);
}
