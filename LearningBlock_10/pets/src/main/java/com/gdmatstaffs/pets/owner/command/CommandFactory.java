package com.gdmatstaffs.pets.owner.command;

import com.gdmatstaffs.pets.address.AddressService;
import com.gdmatstaffs.pets.factory.EntityFactory;
import com.gdmatstaffs.pets.owner.NewOwnerDTO;
import com.gdmatstaffs.pets.user.UserCredentialsDTO;
import com.gdmatstaffs.pets.owner.OwnerRepository;
import com.gdmatstaffs.pets.util.StringHasher;
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
    private final StringHasher stringHasher;

    public Command create(int commandCode, Object... params)
    {
        switch (commandCode)
        {
            case CHECK_CREDENTIALS:
                return new CheckCredentialsCommand(ownerRepository, (UserCredentialsDTO)params[0], stringHasher);
            case CHECK_TOKEN:
                return new CheckTokenCommand(ownerRepository, (String)params[0]);
            case CLEAR_TOKEN:
                return new ClearTokenCommand(ownerRepository, (Integer) params[0]);
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
