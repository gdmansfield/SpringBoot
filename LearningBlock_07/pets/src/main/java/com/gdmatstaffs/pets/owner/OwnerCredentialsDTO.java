package com.gdmatstaffs.pets.owner;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OwnerCredentialsDTO
{
    private final String email;
    private final String password;
}
