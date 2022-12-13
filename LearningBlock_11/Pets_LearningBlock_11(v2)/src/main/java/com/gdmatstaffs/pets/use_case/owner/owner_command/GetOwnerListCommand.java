package com.gdmatstaffs.pets.use_case.owner.owner_command;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.use_case.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetOwnerListCommand implements Command
{
    private final OwnerRepository ownerRepository;

    @Override
    public List<Owner> execute()
    {
        return ownerRepository.findAll();
    }
}
