package com.freedom.controller;

import com.freedom.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminControllerTest extends BaseTest {

    @Test
    void test1() throws Exception {
        mvc.perform(getRequestToken("/api/admin/test"))
                .andExpect(status().isOk());

    }
}