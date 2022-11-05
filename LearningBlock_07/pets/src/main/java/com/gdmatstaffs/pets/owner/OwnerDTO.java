package com.gdmatstaffs.pets.owner;

import com.gdmatstaffs.pets.pet.PetDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class OwnerDTO
{
    private final int id;
    private final String address;
    private final String email;
    private final String name;
    private final String telephone;
    @Setter
    private List<PetDTO> pets;
}
