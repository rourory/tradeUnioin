package com.sts.tradeunion.repositories;

import com.sts.tradeunion.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    boolean deleteById(int id);
    Optional<UserEntity> findByUsername(String username);
}
