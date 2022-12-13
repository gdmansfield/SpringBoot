package com.gdmatstaffs.pets.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
public class StaffCredentialsDTO
{
    @Email(message = "Email is not in the correct format")
    private final String email;

    @NotBlank(message = "Password cannot be blank")
    private final String password;
}
