package com.gdmatstaffs.pets.controller;

import com.gdmatstaffs.pets.dto.StaffCredentialsDTO;
import com.gdmatstaffs.pets.dto.StaffDTO;
import com.gdmatstaffs.pets.dto.DTO_Factory;
import com.gdmatstaffs.pets.use_case.staff.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "/staff")
@Validated
public class StaffController
{
    private final StaffService staffService;
    private final DTO_Factory dto_factory;

    @PostMapping(path = "/checkCredentials")
    public StaffDTO checkCredentials(@Valid @RequestBody StaffCredentialsDTO creds)
    {
        return dto_factory.create(staffService.checkCredentials(creds));
    }

    @PostMapping(path = "/logout/{id}")
    public void logOut(
            @PathVariable(name = "id")
            @Min(value = 1, message = "Id must be greater than zero") int id)
    {
        staffService.clearToken(id);
    }
}
