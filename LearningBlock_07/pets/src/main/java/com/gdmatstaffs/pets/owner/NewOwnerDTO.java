package com.gdmatstaffs.pets.owner;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NewOwnerDTO
{
    private final String address;
    private final String email;
    private final String name;
    private final String telephone;
    private final String password;
}
