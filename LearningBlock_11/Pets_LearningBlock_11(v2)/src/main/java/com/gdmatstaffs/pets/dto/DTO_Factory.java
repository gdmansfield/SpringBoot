package com.gdmatstaffs.pets.dto;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.entity.Pet;
import com.gdmatstaffs.pets.entity.Staff;
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
                    owner.getTelephone(),
                    owner.getToken());

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

        return
                new PetDTO(
                        p.getId(),
                        p.getName(),
                        p.getDateOfBirth(),
                        p.getTypeOfAnimal(),
                        p.getOwner().getId(),
                        p.getOwner().getAddress().toString(),
                        p.getOwner().getEmail(),
                        p.getOwner().getName(),
                        p.getOwner().getTelephone());
    }

    private List<PetDTO> create(List<Pet> pets)
    {
        return pets
                .stream()
                .map(p -> create(p))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public OwnerDTO createOwnerDtoWithNoPets(Owner owner)
    {
        return new OwnerDTO(
                owner.getId(),
                owner.getAddress().toString(),
                owner.getEmail(),
                owner.getName(),
                owner.getTelephone(),
                owner.getToken());
    }

    public StaffDTO create(Staff staff)
    {
        if (staff == null)
        {
            return null;
        }

        StaffDTO staffDTO =
                new StaffDTO(
                        staff.getId(),
                        staff.getEmail(),
                        staff.getName(),
                        staff.getToken());

        return staffDTO;
    }
}
