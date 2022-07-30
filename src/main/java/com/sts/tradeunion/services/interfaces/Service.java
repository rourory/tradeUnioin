package com.sts.tradeunion.services.interfaces;

import java.util.Optional;

public interface Service<T>{
    Optional<T> findById (int id);

}
