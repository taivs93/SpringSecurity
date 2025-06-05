package org.example.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {


    private String message;
    private String jwt;
    private String username;
    private List<String> roles;
    private List<String> permissions;

    public void build(String message,String jwt, String username, List<String> roles, List<String> permissions){
        this.message = message;
        this.jwt = jwt;
        this.username = username;
        this.roles = roles;
        this.permissions = permissions;
    }


}