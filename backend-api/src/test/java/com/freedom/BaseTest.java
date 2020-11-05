package com.freedom;


import com.freedom.cons.AccessTypeEnum;
import com.freedom.model.Admin;
import com.freedom.security.JwtTokenProvider;
import com.freedom.service.AdminService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@Transactional
@Rollback
@AutoConfigureMockMvc
public abstract class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected MockMvc mvc;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    public JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AdminService adminService;

    static String path = "";

    private Gson gson = new Gson();

    protected <T> T persist(T t) {
        entityManager.persist(t);
        return t;
    }

    protected String token(String username, AccessTypeEnum type) {
        return "Bearer " + jwtTokenProvider.token(username, type);
    }

    protected <T> void persist(T... t) {
        for (T it : t
        ) {
            persist(it);
        }
    }

    protected String token(Admin admin) {
        return "Bearer " + jwtTokenProvider.token(admin);
    }

    protected String adminToken() {
        return token(root());
    }

    protected Admin root() {
        return adminService.findByUsernameOrCreate("admin");
    }

    public MockHttpServletRequestBuilder getRequestToken(String url, Object id, String token) {
        return get(url, id).header(HttpHeaders.AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_UTF8);
    }

    public MockHttpServletRequestBuilder getRequestToken(String url, String token) {
        return get(url).header(HttpHeaders.AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_UTF8);
    }

    public MockHttpServletRequestBuilder getRequestToken(String url) {
        return getRequestToken(url, adminToken());
    }

    public MockHttpServletRequestBuilder delRequestToken(String url, String token) {
        return delete(url).header(HttpHeaders.AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_UTF8);
    }

    public MockHttpServletRequestBuilder delRequestToken(String url) {
        return delRequestToken(url, adminToken());
    }

    public MockHttpServletRequestBuilder pathRequestToken(MockHttpServletRequestBuilder pathUrl, String token) {
        return pathUrl.header(HttpHeaders.AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_UTF8);
    }

    public MockHttpServletRequestBuilder pathRequestToken(MockHttpServletRequestBuilder pathUrl, Object data, String token) {
        return pathUrl.header(HttpHeaders.AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_UTF8).content(gson.toJson(data));
    }

    public MockHttpServletRequestBuilder postRequest(String url, Object data) {
        return post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(gson.toJson(data));
    }

    public MockHttpServletRequestBuilder postRequest(String url) {
        return post(url);
    }

    public MockHttpServletRequestBuilder postRequestToken(String url, Object data, String token) {
        return post(url).header(HttpHeaders.AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(data));
    }

    public MockHttpServletRequestBuilder putRequest(String url, Object data) {
        return putRequestToken(url, data);
    }

    public MockHttpServletRequestBuilder postRequestToken(String url, Object data) {
        return postRequestToken(url, data, adminToken());
    }

    public MockHttpServletRequestBuilder putRequestToken(String url, Object data, String token) {
        return put(url).header(HttpHeaders.AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(data));
    }

    public MockHttpServletRequestBuilder putRequestToken(String url, String token) {
        return put(url).header(HttpHeaders.AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_UTF8);
    }


    public MockHttpServletRequestBuilder putRequestToken(String url, Object data) {
        return putRequestToken(url, data, adminToken());
    }

    public MockHttpServletRequestBuilder putRequestToken(String url) {
        return putRequestToken(url, adminToken());
    }
}
