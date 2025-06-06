package org.example.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"users", "permissions"})
public class Role {
    public static String ADMIN = "ADMIN";
    public static String NORMAL = "NORMAL";
    public static String VIP = "VIP";

    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "name",length = 50)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    @JsonIgnore
    private Set<Permission> permissions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
