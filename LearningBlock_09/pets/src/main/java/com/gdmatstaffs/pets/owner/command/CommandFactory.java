package com.gdmatstaffs.pets.owner.command;

import com.gdmatstaffs.pets.address.AddressService;
import com.gdmatstaffs.pets.factory.EntityFactory;
import com.gdmatstaffs.pets.owner.NewOwnerDTO;
import com.gdmatstaffs.pets.owner.OwnerCredentialsDTO;
import com.gdmatstaffs.pets.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.gdmatstaffs.pets.owner.command.Command.*;

@Component
@RequiredArgsConstructor
public class CommandFactory
{
    private final AddressService addressService;
    private final OwnerRepository ownerRepository;
    private final EntityFactory entityFactory;

    public Command create(int commandCode, Object... params)
    {
        switch (commandCode)
        {
            case CHECK_CREDENTIALS:
                return new CheckCredentialsCommand(ownerRepository, (OwnerCredentialsDTO)params[0]);
            case CREATE_OWNER:
                return new CreateOwnerCommand(addressService, ownerRepository, entityFactory, (NewOwnerDTO)params[0]);
            case DELETE_OWNER:
                return new DeleteOwnerCommand(ownerRepository, (Integer)params[0]);
            case UPDATE_OWNER_ADDRESS:
                return new UpdateOwnerAddressCommand(addressService, ownerRepository, entityFactory, (Integer)params[0], (String)params[1]);
            case GET_OWNER:
                return new GetOwnerCommand(ownerRepository, (Integer)params[0]);
            case GET_OWNER_LIST:
                return new GetOwnerListCommand(ownerRepository);
            default:
                return null;
        }
    }
}
