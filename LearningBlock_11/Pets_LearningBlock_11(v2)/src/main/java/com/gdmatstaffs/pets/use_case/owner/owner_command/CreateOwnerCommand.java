package com.gdmatstaffs.pets.use_case.owner.owner_command;

import com.gdmatstaffs.pets.use_case.address.AddressService;
import com.gdmatstaffs.pets.entity.Address;
import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.dto.NewOwnerDTO;
import com.gdmatstaffs.pets.use_case.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateOwnerCommand implements Command
{
    private final AddressService addressService;
    private final OwnerRepository ownerRepository;
    private final EntityFactory entityFactory;
    private final NewOwnerDTO owner;

    @Override
    public Owner execute()
    {
        Owner possibleNewOwner = entityFactory.create(owner);

        if (emailAlreadyInUse(possibleNewOwner.getEmail()))
        {
            return null;
        }

        Address address = possibleNewOwner.getAddress();
        Address addressInRepository = addressService.findOrCreateAddressInRepo(address);

        possibleNewOwner.setAddress(addressInRepository);

        return ownerRepository.save(possibleNewOwner);
    }

    private boolean emailAlreadyInUse(String email)
    {
        return ownerRepository.findByEmail(email) != null;
    }
}
