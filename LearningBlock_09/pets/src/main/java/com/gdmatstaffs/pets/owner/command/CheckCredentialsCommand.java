package com.gdmatstaffs.pets.owner.command;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.owner.OwnerCredentialsDTO;
import com.gdmatstaffs.pets.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckCredentialsCommand implements Command
{
    private final OwnerRepository ownerRepository;
    private final OwnerCredentialsDTO credentials;

    @Override
    public Owner execute()
    {
        Owner owner = ownerRepository.findByEmail(credentials.getEmail());

        if (owner != null && owner.getPassword().equals(credentials.getPassword()))
        {
            return owner;
        }

        return null;
    }
}
