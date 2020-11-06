package com.freedom.mapper;


import com.freedom.model.Admin;
import com.freedom.vo.AdminInfoVO;
import com.freedom.vo.AdminVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminVO adminToAdminInfoVO(Admin admin);
    AdminInfoVO toInfoVO(Admin admin);
}
