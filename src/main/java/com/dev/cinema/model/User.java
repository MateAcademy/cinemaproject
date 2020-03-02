package com.dev.cinema.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

/**
 * @author Sergey Klunniy
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    @Email(message = "{user.email.invalid}")
    private String email;

    @Column(name = "password")
    @Size(max = 200, min = 3, message = "{user.password.invalid}")
    private String password;

    @Column(name = "roles")
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    private byte[] salt;

    public User(@Email(message = "{user.email.invalid}") String email,
                @Size(max = 200, min = 3, message = "{user.password.invalid}")
                        String password, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
