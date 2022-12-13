package com.gdmatstaffs.pets.use_case.owner.owner_command;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.use_case.owner.OwnerRepository;
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
