package com.gdmatstaffs.pets.pet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class NewPetDTO
{
    @NotBlank(message = "Name cannot be blank")
    private final String name;

    @Past(message = "Date of birth must be before today")
    private final LocalDate dateOfBirth;

    @NotBlank(message = "Type of animal cannot be blank")
    private final String typeOfAnimal;

    @Min(value = 1, message = "Owner id must be greater than zero")
    private final int ownerId;
}
