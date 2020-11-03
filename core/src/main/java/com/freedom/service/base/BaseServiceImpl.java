package com.freedom.service.base;

import com.freedom.model.base.Deletable;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 抽象服务实现
 *
 * @param <T>
 * @author yu
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private JpaRepository<T, Long> jpa;

    @Override
    public  void setRepository(JpaRepository jpaRepository) {
        jpa = jpaRepository;
    }

    @Override
    public <S extends T> S save(S entity) {
        return jpa.save(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        jpa.deleteAll(entities);
    }

    public void deleteInBatch(Iterable<T> iterable) {
        jpa.deleteInBatch(iterable);
    }

    public void deleteAllInBatch() {
        jpa.deleteAllInBatch();
    }

    public T getOne(Long aLong) {
        return jpa.getOne(aLong);
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return jpa.findAll(example);
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return jpa.findAll(example, sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return jpa.findAll(pageable);
    }


    public Optional<T> findById(Long aLong) {
        return jpa.findById(aLong);
    }

    public void deleteById(Long aLong) {
        jpa.deleteById(aLong);
    }

    public void delete(T entity) {
        jpa.delete(entity);
    }

    public void deleteAll() {
        jpa.deleteAll();
    }

    public <S extends T> Optional<S> findOne(Example<S> example) {
        return jpa.findOne(example);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return jpa.findAll(example, pageable);
    }

    public <S extends T> long count(Example<S> example) {
        return jpa.count(example);
    }

    public <S extends T> boolean exists(Example<S> example) {
        return jpa.exists(example);
    }

    @Override
    public List<T> findAll() {
        return jpa.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return jpa.findAll(sort);
    }

    @Override
    public List<T> findAllById(Iterable<Long> ids) {
        return jpa.findAllById(ids);
    }

    @Override
    public Optional<T> get(Long id) {
        return jpa.findById(id);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return jpa.saveAll(entities);
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return jpa.saveAndFlush(entity);
    }

    @Override
    public void flush() {
        jpa.flush();
    }

    @Override
    public Page<T> page(Pageable pageable) {
        return jpa.findAll(pageable);
    }

    @Override
    public Page<T> page(int page, int size) {
        return page(PageRequest.of(page, size));
    }

    @Override
    public long count() {
        return jpa.count();
    }

    @Override
    public void delete(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpa.existsById(id);
    }

    @Override
    public void softDel(Long id) {
        get(id).ifPresent(
                it -> {
                    if (it instanceof Deletable) {
                        ((Deletable) it).setHasDelete(true);
                        ((Deletable) it).setDeleteAt(ZonedDateTime.now());
                        save(it);
                    } else {
                        throw new RuntimeException("该对象不支持软删除");
                    }
                }
        );
    }

    @Override
    public T findOrThrow(Long id) {
        return get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
