package com.gdmatstaffs.pets.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class OwnerDTO
{
    @Min(value = 1, message = "Id must be greater than zero")
    private final int id;

    @NotBlank(message = "Address cannot be blank")
    private final String address;

    @Email(message = "Email is not in the correct format")
    private final String email;

    @NotBlank(message = "Name cannot be blank")
    private final String name;

    @NotBlank(message = "Telephone cannot be blank")
    private final String telephone;

    @NotBlank(message = "Token cannot be blank")
    private final String token;

    @Setter
    private List<PetDTO> pets;
}
