package com.gdmatstaffs.pets.staff;

import com.gdmatstaffs.pets.pet.PetDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class StaffDTO
{
    @Min(value = 1, message = "Id must be greater than zero")
    private final int id;

    @Email(message = "Email is not in the correct format")
    private final String email;

    @NotBlank(message = "Name cannot be blank")
    private final String name;

    @NotBlank(message = "Token cannot be blank")
    private final String token;
}
