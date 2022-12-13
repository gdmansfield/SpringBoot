package com.gdmatstaffs.pets.use_case.owner.owner_command;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.use_case.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckTokenCommand implements Command
{
    private final OwnerRepository ownerRepository;
    private final String token;

    @Override
    public Owner execute()
    {
        Owner owner = ownerRepository.findByToken(token);
        if (owner != null && owner.getToken() != null)
        {
            return owner;
        }

        return null;
    }
}
