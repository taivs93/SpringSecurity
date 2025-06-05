package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "username",length = 50)
    private String userName;

    @Column(name = "password",length = 255)
    private String password;

    @Column(name = "email",length = 100)
    private String email;

    @Column(name = "tel",length = 20)
    private String tel;

    @Column(name = "enabled")
    private byte enabled = 1;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
