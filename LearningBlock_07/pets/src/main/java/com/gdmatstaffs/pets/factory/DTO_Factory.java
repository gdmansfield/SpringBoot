package com.gdmatstaffs.pets.factory;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.entity.Pet;
import com.gdmatstaffs.pets.owner.OwnerDTO;
import com.gdmatstaffs.pets.pet.PetDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTO_Factory
{
    public OwnerDTO create(Owner owner)
    {
        if (owner == null)
        {
            return null;
        }

        OwnerDTO ownerDTO =
                new OwnerDTO(
                    owner.getId(),
                    owner.getAddress().toString(),
                    owner.getEmail(),
                    owner.getName(),
                    owner.getTelephone());

        if (owner.getPets() != null)
        {
            ownerDTO.setPets(create(owner.getPets()));
        }

        return ownerDTO;
    }

    public PetDTO create(Pet p)
    {
        if (p == null)
        {
            return null;
        }

        OwnerDTO owner = createOwnerDtoWithNoPets(p.getOwner());

        return
                new PetDTO(
                        p.getId(),
                        p.getName(),
                        p.getDateOfBirth(),
                        owner,
                        p.getTypeOfAnimal());
    }

    private PetDTO create(Pet p, OwnerDTO owner)
    {
        return
                new PetDTO(
                        p.getId(),
                        p.getName(),
                        p.getDateOfBirth(),
                        owner,
                        p.getTypeOfAnimal());
    }

    private List<PetDTO> create(List<Pet> pets)
    {
        return pets
                .stream()
                .map(p -> create(p, null))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public OwnerDTO createOwnerDtoWithNoPets(Owner owner)
    {
        return new OwnerDTO(
                owner.getId(),
                owner.getAddress().toString(),
                owner.getEmail(),
                owner.getName(),
                owner.getTelephone());
    }
}
