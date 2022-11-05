package com.gdmatstaffs.pets.pet;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.entity.Pet;
import com.gdmatstaffs.pets.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor

@Service
public class PetService
{
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public Pet createPet(PetDTO pet)
    {
        Optional<Owner> owner = ownerRepository.findById(pet.getOwner().getId());

        if (owner.isPresent())
        {
            Pet p = new Pet(pet.getId(),
                            pet.getName(),
                            pet.getDateOfBirth(),
                            owner.get(),
                            pet.getTypeOfAnimal());

            return petRepository.save(p);
        }

        return null;
    }

    public Pet getPet(int id)
    {
        return petRepository.findById(id).orElse(null);
    }
}
