package com.gdmatstaffs.pets.use_case.owner;

import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.dto.NewOwnerDTO;
import com.gdmatstaffs.pets.dto.OwnerCredentialsDTO;
import com.gdmatstaffs.pets.use_case.owner.owner_command.Command;
import com.gdmatstaffs.pets.use_case.owner.owner_command.CommandFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService
{
    private final CommandFactory commandFactory;

    public void clearToken(int id)
    {
        commandFactory
            .create(Command.CLEAR_TOKEN, id)
            .execute();
    }

    public Owner checkCredentials(OwnerCredentialsDTO creds)
    {
        return
                (Owner)commandFactory
                        .create(Command.CHECK_CREDENTIALS, creds)
                        .execute();
    }

    public Owner checkCredentials(String token)
    {
        return
                (Owner)commandFactory
                        .create(Command.CHECK_TOKEN, token)
                        .execute();
    }

    public Owner createOwner(NewOwnerDTO owner)
    {
        return
                (Owner)commandFactory
                        .create(Command.CREATE_OWNER, owner)
                        .execute();
    }

    public boolean deleteOwner(int id)
    {
        return
                (boolean)commandFactory
                        .create(Command.DELETE_OWNER, id)
                        .execute();
    }

    public Owner getOwner(int id)
    {
        return
                (Owner)commandFactory
                        .create(Command.GET_OWNER, id)
                        .execute();
    }

    public List<Owner> getOwnerList()
    {
        return
                (List<Owner>)commandFactory
                        .create(Command.GET_OWNER_LIST)
                        .execute();
    }

    public Owner updateOwnerAddress(int ownerId, String addr)
    {
        return
                (Owner)commandFactory
                        .create(Command.UPDATE_OWNER_ADDRESS, ownerId, addr)
                        .execute();

    }
}
