package ru.kata.spring.boot_security.demo.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@ToString
@RequiredArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String roleName;


    @ManyToMany(mappedBy = "roles")
    private List<Users> users;

    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
