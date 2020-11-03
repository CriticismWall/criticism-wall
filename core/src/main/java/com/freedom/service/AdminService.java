package com.freedom.service;

import com.freedom.model.Admin;
import com.freedom.service.base.BaseService;

public interface AdminService extends BaseService<Admin> {

    Admin create();

    void findTopBy();

}
