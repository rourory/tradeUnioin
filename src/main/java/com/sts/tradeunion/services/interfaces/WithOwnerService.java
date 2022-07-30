package com.sts.tradeunion.services.interfaces;

import com.sts.tradeunion.entities.PersonEntity;

import java.util.List;

public interface WithOwnerService<T> extends Service<T> {

    T save (T entity, int ownerId);

    T update (T entity, int ownerId);

    boolean delete(PersonEntity owner, int id);
    List<T> findByOwnerId(int ownerId);
}