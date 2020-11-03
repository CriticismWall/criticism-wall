package com.freedom.service.base;


import com.freedom.model.base.Deletable;

import java.util.Optional;

public abstract class DeletableServiceImpl<T extends Deletable> extends BaseServiceImpl<T> implements DeletableService<T> {

    @Override
    public Optional<T> findByHasDeleteIsFalse(Long id) {
        return Optional.empty();
    }

    @Override
    public T findByHasDeleteIsFalseOrThrow(Long id) {
        return null;
    }
}
