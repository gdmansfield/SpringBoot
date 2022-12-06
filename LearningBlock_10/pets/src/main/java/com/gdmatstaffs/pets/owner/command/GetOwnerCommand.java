package com.gdmatstaffs.pets.owner.command;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetOwnerCommand implements Command
{
    private final OwnerRepository ownerRepository;
    private final int id;

    @Override
    public Owner execute()
    {
        return ownerRepository.findById(id).orElse(null);
    }
}
