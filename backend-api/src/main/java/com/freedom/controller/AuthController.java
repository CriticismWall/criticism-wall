package com.freedom.controller;


import com.freedom.mapper.AdminMapper;
import com.freedom.model.Admin;
import com.freedom.request.LoginRequest;
import com.freedom.response.JwtAuthenticationResponse;
import com.freedom.security.CurrentUser;
import com.freedom.security.JwtTokenProvider;
import com.freedom.security.UserOrThrow;
import com.freedom.service.AdminService;
import com.freedom.vo.AdminInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static com.freedom.cons.AccessTypeEnum.ADMIN;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "后台管理 - 认证")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(com.freedom.controller.AuthController.class);

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AdminService adminService;


    @PostMapping("/sign")
    @ApiOperation(value = "登录")
    public JwtAuthenticationResponse sign(@Valid @RequestBody LoginRequest loginRequest) {

        Optional<Admin> admin = adminService.findByUsername(loginRequest.getUsername());
        Admin existsAdmin = admin.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        if (!passwordEncoder.matches(loginRequest.getPassword(), existsAdmin.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        logger.info("login a admin whose username:{}", loginRequest.getUsername());

        return new JwtAuthenticationResponse(tokenProvider.token(loginRequest.getUsername(), ADMIN));
    }


    @GetMapping("/privileges")
    @ApiOperation(value = "当前用户的权限")
    public List<String> privileges(@CurrentUser Admin admin) {
        return admin.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList());
    }

    @GetMapping("/currentInfo")
    @ApiOperation(value = "当前用户详情")
    public AdminInfoVO info(@UserOrThrow Admin admin) {
        Admin persist = adminService.findOrThrow(admin.getId());
        return adminMapper.toInfoVO(persist);
    }


}
