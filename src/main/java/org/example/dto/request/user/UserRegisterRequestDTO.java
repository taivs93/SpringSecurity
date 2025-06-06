package org.example.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRegisterRequestDTO {

    @NotBlank(message = "User name can not be blank")
    private String userName;

    @NotBlank(message = "Password can not be blank")
    private String password;

    @NotBlank(message = "Retype password can not be blank")
    private String retypePassword;

    @NotBlank(message = "Email can not be blank")
    private String email;

    @NotBlank(message = "Tel can not be blank")
    private String tel;
}
