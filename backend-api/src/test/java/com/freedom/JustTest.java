package com.freedom;

import com.freedom.model.Admin;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@Rollback
@AutoConfigureMockMvc
public abstract class JustTest<E> {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected EntityManager entityManager;

    String defaultRequestMapping = "";

    Class<E> defaultEntityClazz = null;

    private Gson gson =  new Gson();

    protected Admin mockMember = null;




}
