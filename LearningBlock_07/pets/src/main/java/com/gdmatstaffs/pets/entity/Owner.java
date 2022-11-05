package com.gdmatstaffs.pets.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "pet_owner")
public class Owner
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    private String email;
    private String name;
    private String password;
    private String telephone;

    @OneToMany(mappedBy = "owner")
    @OrderBy(value = "name")
    private List<Pet> pets;
}
