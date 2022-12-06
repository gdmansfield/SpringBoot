package com.gdmatstaffs.pets.owner;

import com.gdmatstaffs.pets.factory.DTO_Factory;
import com.gdmatstaffs.pets.user.UserCredentialsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "/owner")
@Validated
public class OwnerController
{
    private final OwnerService ownerService;
    private final DTO_Factory dto_factory;

    @PostMapping(path = "/checkCredentials")
    public OwnerDTO checkCredentials(@Valid @RequestBody UserCredentialsDTO creds)
    {
        return dto_factory.create(ownerService.checkCredentials(creds));
    }

    @PostMapping(path = "/create")
    public OwnerDTO createOwner(@Valid @RequestBody NewOwnerDTO owner)
    {
        return dto_factory.create(ownerService.createOwner(owner));
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteOwner(
            @PathVariable(name = "id")
            @Min(value = 1, message = "Id must be greater than zero") int id)
    {
        return ownerService.deleteOwner(id);
    }

    @GetMapping(path = "/get/{id}")
    public OwnerDTO getOwner(
            @PathVariable(name = "id")
            @Min(value = 1, message = "Id must be greater than zero") int id)
    {
        return dto_factory.create(ownerService.getOwner(id));
    }

    @GetMapping(path = "/get/all")
    public List<OwnerDTO> getOwnerList()
    {
        return ownerService
                .getOwnerList()
                .stream()
                .map(e -> dto_factory.createOwnerDtoWithNoPets(e))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @PutMapping(path = "/update/{id}/address")
    public OwnerDTO updateOwnerAddress(
            @PathVariable(name = "id")
            @Min(value = 1, message = "Id must be greater than zero") int id,
            @Valid @RequestBody @NotBlank(message = "Address cannot be blank") String address)
    {
        return dto_factory.create(
                ownerService.updateOwnerAddress(id, address));
    }

    @PostMapping(path = "/logout/{id}")
    public void logOut(
            @PathVariable(name = "id")
            @Min(value = 1, message = "Id must be greater than zero") int id)
    {
        ownerService.clearToken(id);
    }
}
