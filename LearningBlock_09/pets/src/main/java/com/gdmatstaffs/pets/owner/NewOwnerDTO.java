package com.gdmatstaffs.pets.owner;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class NewOwnerDTO
{
    @NotBlank(message = "Address cannot be blank")
    private final String address;

    @NotNull(message = "Email must have a value")
    @Email(message = "Email is not in the correct format")
    private final String email;

    @NotBlank(message = "Name cannot be blank")
    private final String name;

    @NotBlank(message = "Telephone cannot be blank")
    private final String telephone;

    @NotBlank(message = "Password cannot be blank")
    private final String password;
}
