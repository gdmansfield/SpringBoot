package com.gdmatstaffs.pets.use_case.pet;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.entity.Pet;
import com.gdmatstaffs.pets.dto.NewPetDTO;
import com.gdmatstaffs.pets.use_case.owner.OwnerDoesNotExistException;
import com.gdmatstaffs.pets.use_case.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor

@Service
public class PetService
{
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public Pet createPet(NewPetDTO pet)
    {
        Optional<Owner> owner = ownerRepository.findById(pet.getOwnerId());

        if (owner.isPresent())
        {
            Pet p = new Pet(0,
                            pet.getName(),
                            pet.getDateOfBirth(),
                            owner.get(),
                            pet.getTypeOfAnimal());

            return petRepository.save(p);
        }

        throw new OwnerDoesNotExistException(pet.getOwnerId());
    }

    public Pet getPet(int id)
    {
        return petRepository.findById(id).orElse(null);
    }
}
