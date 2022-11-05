package com.gdmatstaffs.pets.owner;

import com.gdmatstaffs.pets.factory.DTO_Factory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "/owner")
public class OwnerController
{
    private final OwnerService ownerService;
    private final DTO_Factory dto_factory;

    @PostMapping(path = "/checkCredentials")
    public OwnerDTO checkCredentials(@RequestBody OwnerCredentialsDTO creds)
    {
        return dto_factory.create(ownerService.checkCredentials(creds));
    }

    @PostMapping(path = "/create")
    public OwnerDTO createOwner(@RequestBody NewOwnerDTO owner)
    {
        return dto_factory.create(ownerService.createOwner(owner));
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteOwner(@PathVariable(name = "id") int id)
    {
        return ownerService.deleteOwner(id);
    }

    @GetMapping(path = "/{id}")
    public OwnerDTO getOwner(@PathVariable(name = "id") int id)
    {
        return dto_factory.create(ownerService.getOwner(id));
    }

    @GetMapping(path = "/all")
    public List<OwnerDTO> getOwnerList()
    {
        return ownerService
                .getOwnerList()
                .stream()
                .map(e -> dto_factory.createOwnerDtoWithNoPets(e))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @PostMapping(path = "/{id}/update/address")
    public OwnerDTO updateOwnerAddress(
            @PathVariable(name = "id") int id,
            @RequestBody String address)
    {
        return dto_factory.create(
                ownerService.updateOwnerAddress(id, address));
    }
}
