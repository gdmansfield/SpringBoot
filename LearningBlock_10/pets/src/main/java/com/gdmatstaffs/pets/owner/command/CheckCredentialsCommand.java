package com.gdmatstaffs.pets.owner.command;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.user.UserCredentialsDTO;
import com.gdmatstaffs.pets.owner.OwnerRepository;
import com.gdmatstaffs.pets.util.StringHasher;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class CheckCredentialsCommand implements Command
{
    private final OwnerRepository ownerRepository;
    private final UserCredentialsDTO credentials;
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
