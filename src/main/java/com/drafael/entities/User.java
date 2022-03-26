package com.drafael.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    @Getter@Setter
    private Integer id;

    @Getter @Setter @Column(name = "name")
    private String username;

    @Getter @Setter @Column(name = "password")
    private String password;


    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @Getter @Setter
    private Profile profile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}