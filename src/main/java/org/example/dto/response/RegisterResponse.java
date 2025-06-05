package org.example.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import org.example.model.User;


@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("tel")
    private String tel;

    public void build(User user){
        this.setUserName(user.getUserName());
        this.setTel(user.getTel());
        this.setEmail(user.getEmail());
    }
}
