package com.gdmatstaffs.pets.pet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class PetDTO
{
    @Min(value = 1, message = "Id must be greater than zero")
    private final int id;

    @NotBlank(message = "Name cannot be blank")
    private final String name;

    @Past(message = "Date of birth must be before today")
    private final LocalDate dateOfBirth;

    @NotBlank(message = "Type of animal cannot be blank")
    private final String typeOfAnimal;

    @Min(value = 1, message = "Owner id must be greater than zero")
    private final int ownerId;

    @NotBlank(message = "Owner address cannot be blank")
    private final String ownerAddress;

    @Email(message = "Owner email is not in the correct format")
    private final String ownerEmail;

    @NotBlank(message = "Owner name cannot be blank")
    private final String ownerName;

    @NotBlank(message = "Owner telephone cannot be blank")
    private final String ownerTelephone;
}
