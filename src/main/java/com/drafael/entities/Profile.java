package com.drafael.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter@Setter
    private Integer id;

    @Column(name = "firstName")@Getter@Setter
    private String firstName;

    @Column(name = "lastname")@Getter@Setter
    private String lastName;

    @Column(name = "birthdate")@Getter@Setter
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @OneToOne
    @JoinColumn(name ="user_id", referencedColumnName ="id")@Getter@Setter
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return id.equals(profile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}