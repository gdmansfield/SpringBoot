package com.gdmatstaffs.pets.pet;

import com.gdmatstaffs.pets.exception.OwnerIdsDoNotMatchException;
import com.gdmatstaffs.pets.factory.DTO_Factory;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "/pet")
@Validated
public class PetController
{
    private final PetService petService;
    private final DTO_Factory dto_factory;

    @PostMapping(path = "/add/{owner_id}")
    public PetDTO addPet(
            @PathVariable("owner_id") int ownerId,
            @Valid @RequestBody NewPetDTO pet)
    {
        if (pet.getOwner().getId() != ownerId)
        {
            throw new OwnerIdsDoNotMatchException(ownerId, pet.getOwner().getId());
        }
        return dto_factory.create(petService.createPet(pet));
    }

    @GetMapping(path = "/get/{id}")
    public PetDTO getPet(
            @PathVariable(name = "id")
            @Min(value = 1, message = "Id must be greater than zero") int id)
    {
        return dto_factory.create(petService.getPet(id));
    }
}
