package com.gdmatstaffs.pets.pet;

import com.gdmatstaffs.pets.owner.OwnerDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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

    @NotNull(message = "Owner must be present")
    private final OwnerDTO owner;


    @NotBlank(message = "Type of animal cannot be blank")
    private final String typeOfAnimal;
}
