package com.gdmatstaffs.pets.pet;

import com.gdmatstaffs.pets.factory.DTO_Factory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "/pet")
public class PetController
{
    private final PetService petService;
    private final DTO_Factory dto_factory;

    @PostMapping(path = "/add")
    public PetDTO addPet(@RequestBody PetDTO pet)
    {
        return dto_factory.create(petService.createPet(pet));
    }

    @GetMapping(path = "/{id}")
    public PetDTO getPet(@PathVariable(name = "id") int id)
    {
        return dto_factory.create(petService.getPet(id));
    }
}
