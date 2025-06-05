package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Permission {

    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "name",length = 50)
    private String name;

    @ManyToMany(mappedBy = "permissions",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission permission = (Permission) o;
        return Objects.equals(id, permission.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
