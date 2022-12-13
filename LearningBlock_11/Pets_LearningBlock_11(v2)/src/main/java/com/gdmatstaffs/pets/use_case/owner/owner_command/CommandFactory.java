package com.gdmatstaffs.pets.use_case.owner.owner_command;

import com.gdmatstaffs.pets.use_case.address.AddressService;
import com.gdmatstaffs.pets.dto.NewOwnerDTO;
import com.gdmatstaffs.pets.dto.OwnerCredentialsDTO;
import com.gdmatstaffs.pets.use_case.owner.OwnerRepository;
import com.gdmatstaffs.pets.use_case.util.StringHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.gdmatstaffs.pets.use_case.owner.owner_command.Command.*;

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
                return new CheckCredentialsCommand(ownerRepository, (OwnerCredentialsDTO)params[0], stringHasher);
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
