package com.freedom.model;


import lombok.*;



/**
 * 管理员
 *
 * @author yu
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {


    private String name = "";


    private String username;

    private String password = "";

    private String phone;


}