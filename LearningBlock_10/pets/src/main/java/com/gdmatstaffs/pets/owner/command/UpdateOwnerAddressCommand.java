package com.gdmatstaffs.pets.owner.command;

import com.gdmatstaffs.pets.address.AddressService;
import com.gdmatstaffs.pets.entity.Address;
import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.factory.EntityFactory;
import com.gdmatstaffs.pets.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateOwnerAddressCommand implements Command
{
    private final AddressService addressService;
    private final OwnerRepository ownerRepository;
    private final EntityFactory entityFactory;
    private final int ownerId;
    private final String address;

    @Override
    public Owner execute()
    {
        Optional<Owner> optionalOwner = ownerRepository.findById(ownerId);

        if (!optionalOwner.isPresent())
        {
            return null;
        }

        Address newAddress = entityFactory.create(address);
        Address addressInRepository = addressService.findOrCreateAddressInRepo(newAddress);

        Owner ownerEntity = optionalOwner.get();
        ownerEntity.setAddress(addressInRepository);

        return ownerRepository.save(ownerEntity);
    }
}
