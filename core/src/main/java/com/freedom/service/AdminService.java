package com.freedom.service;

import com.freedom.model.Admin;
import com.freedom.service.base.BaseService;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;

public interface AdminService extends BaseService<Admin> {

    Admin create();

    void findTopBy();

    Optional<Admin> findByUsername(String username);

    Collection<? extends GrantedAuthority> getGrantedAuthority(String userId);

}
