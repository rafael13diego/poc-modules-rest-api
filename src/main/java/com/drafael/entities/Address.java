package com.drafael.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //@Column(name = "id")
    @Getter @Setter
    private Integer id;

    @Getter @Setter @Column(name = "street")
    private String street;

    @Getter @Setter @Column(name = "number")
    private String number;

    @Getter @Setter @Column(name = "city")
    private String city;

    @ManyToOne
    private Profile profile;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
