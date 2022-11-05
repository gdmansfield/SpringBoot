package com.gdmatstaffs.pets.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "address")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String houseNumber;
    private String street;
    private String town;
    private String postcode;

    @Override
    public String toString()
    {
        return String.format(
                "%s, %s, %s, %s",
                houseNumber,
                street,
                town,
                postcode);
    }
}
