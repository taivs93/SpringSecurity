package org.example.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserLoginRequestDTO {
    @JsonProperty("user_name")
    @NotBlank(message = "username can not be blank")
    private String userName;

    @NotBlank(message = "Password can not be blank")
    private String password;
}
