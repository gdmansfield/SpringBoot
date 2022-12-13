package com.gdmatstaffs.pets.use_case.owner.owner_command;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.use_case.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClearTokenCommand implements Command
{
    private final OwnerRepository ownerRepository;
    private final int id;

    @Override
    public Object execute()
    {
        Owner owner = ownerRepository.findById(id).orElse(null);
        if (owner != null && owner.getToken() != null)
        {
            owner.setToken(null);
            ownerRepository.save(owner);
        }

        return null;
    }
}
