package com.freedom.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * 抽象服务
 *
 * @param <T>
 * @author yu
 */
@NoRepositoryBean
public interface BaseService<T> extends JpaRepository<T, Long> {

    void setRepository(JpaRepository jpaRepository);

    Optional<T> get(Long id);

    void flush();

    /**
     * 分页查询
     *
     * @param pageable 分页条件
     * @return Page
     */
    Page<T> page(Pageable pageable);


    /**
     * 分页查询
     *
     * @param page 页码
     * @param size 页数
     * @return Page
     */
    Page<T> page(int page, int size);

    /**
     * 删除一个实体
     */
    void delete(Long id);

    boolean existsById(Long id);

    void softDel(Long id);

    T findOrThrow(Long id);
}
