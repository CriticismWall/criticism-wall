package com.freedom.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "LoginRequest", description = "登陆")
public class LoginRequest {
  @NotBlank
  private String username;

  @NotBlank
  private String password;

}
