package com.freedom.service.base;


import com.freedom.model.base.Deletable;

import java.util.Optional;

public interface DeletableService<T extends Deletable> extends BaseService<T> {

    Optional<T> findByHasDeleteIsFalse(Long id);

    T findByHasDeleteIsFalseOrThrow(Long id);

    @Override
    default void softDel(Long id) {
        T del = findOrThrow(id);
        del.delete();
        save(del);
    }
}
