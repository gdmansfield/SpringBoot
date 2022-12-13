package com.gdmatstaffs.pets.use_case.owner.owner_command;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.dto.OwnerCredentialsDTO;
import com.gdmatstaffs.pets.use_case.owner.OwnerRepository;
import com.gdmatstaffs.pets.use_case.util.StringHasher;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class CheckCredentialsCommand implements Command
{
    private final OwnerRepository ownerRepository;
    private final OwnerCredentialsDTO credentials;
    private final StringHasher stringHasher;

    @Override
    public Owner execute()
    {
        Owner owner = ownerRepository.findByEmail(credentials.getEmail());

        if (owner != null && owner.getPassword().equals(credentials.getPassword()))
        {
            String token =
                    stringHasher.hashString(
                            owner.getEmail() + ":" + LocalDate.now().toString());
            owner.setToken(token);
            owner = ownerRepository.save(owner);
            return owner;
        }

        return null;
    }
}
