package com.freedom.model;


import com.freedom.model.base.Deletable;
import lombok.*;

import javax.persistence.Entity;


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
@Entity
public class Admin extends Deletable {


    private String name ;

    private String username;

    private String password ;

    private String phone;

}