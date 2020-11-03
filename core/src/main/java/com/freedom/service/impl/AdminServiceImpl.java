package com.freedom.service.impl;

import com.freedom.model.Admin;
import com.freedom.model.QAdmin;
import com.freedom.repository.AdminRepository;
import com.freedom.service.AdminService;
import com.freedom.service.base.BaseServiceImpl;
import com.google.common.collect.Lists;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    private QAdmin qAdmin = QAdmin.admin;

    @Autowired
    public void setRepository(AdminRepository adminRepository) {
        super.setRepository(adminRepository);
    }


    @Override
    public Admin create() {
        Admin username = Admin.builder()
                .username("dzl").build();
        return save(username);
    }

    @Override
    public void findTopBy() {
        BooleanExpression expression = qAdmin.username.eq("dzl");

        Lists.newArrayList(adminRepository.findAll(expression));
    }

    @Override
    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthority(String userId) {
        Optional<Admin> optional = adminRepository.findByUsername(userId);
        if (optional.isPresent()) {
            Admin admin = optional.get();
            return admin.getAuthorities();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "没有找到用户");
        }
    }

}

