package org.example.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private Integer id;
    private String userName;
    private String email;
    @JsonIgnore
    private String password;
    private String tel;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (!user.getRoles().isEmpty()){
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName().toUpperCase()));
                if (!role.getPermissions().isEmpty()){
                    role.getPermissions().forEach(permission -> {
                        authorities.add(new SimpleGrantedAuthority(permission.getName().toUpperCase()));
                    });
                }
            });
        }
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setId(user.getId());
        userDetails.setUserName(user.getUserName());
        userDetails.setPassword(user.getPassword());
        userDetails.setEmail(user.getEmail());
        userDetails.setTel(user.getTel());
        userDetails.setEnabled(user.getEnabled() == 1);
        userDetails.setAuthorities(authorities);
        return userDetails;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
