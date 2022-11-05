package com.gdmatstaffs.pets.pet;

import com.gdmatstaffs.pets.owner.OwnerDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class PetDTO
{
    private final int id;
    private final String name;
    private final LocalDate dateOfBirth;
    private final OwnerDTO owner;
    private final String typeOfAnimal;
}
