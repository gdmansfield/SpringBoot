package com.gdmatstaffs.pets.owner.command;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.owner.OwnerRepository;
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
