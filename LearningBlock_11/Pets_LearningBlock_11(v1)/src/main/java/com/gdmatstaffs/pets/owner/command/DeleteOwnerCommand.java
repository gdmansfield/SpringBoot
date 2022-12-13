package com.gdmatstaffs.pets.owner.command;

import com.gdmatstaffs.pets.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteOwnerCommand implements Command
{
    private final OwnerRepository ownerRepository;
    private final int id;

    @Override
    public Boolean execute()
    {
        if (ownerRepository.existsById(id))
        {
            try
            {
                ownerRepository.deleteById(id);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
                return false;
            }
        }

        return false;
    }
}
