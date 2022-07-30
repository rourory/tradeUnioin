package com.sts.tradeunion.services.interfaces;

public interface WithoutOwnerService<T> extends Service<T>{
    T save (T entity);
    boolean deleteById(int id);
    T update (T entity);
}
