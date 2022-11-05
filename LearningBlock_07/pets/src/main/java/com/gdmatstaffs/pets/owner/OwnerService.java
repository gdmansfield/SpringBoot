package com.gdmatstaffs.pets.owner;

import com.gdmatstaffs.pets.address.AddressRepository;
import com.gdmatstaffs.pets.entity.Address;
import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.factory.EntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class OwnerService
{
    private final AddressRepository addressRepository;
    private final OwnerRepository ownerRepository;
    private final EntityFactory entityFactory;

    public Owner checkCredentials(OwnerCredentialsDTO creds)
    {
        Owner owner = ownerRepository.findByEmail(creds.getEmail());

        if (owner != null && owner.getPassword().equals(creds.getPassword()))
        {
            return owner;
        }

        return null;
    }

    public Owner createOwner(NewOwnerDTO owner)
    {
        Owner possibleNewOwner = entityFactory.create(owner);

        if (emailAlreadyInUse(possibleNewOwner.getEmail()))
        {
            return null;
        }

        Address address = possibleNewOwner.getAddress();
        Address addressInRepository = findOrCreateAddressInRepo(address);

        possibleNewOwner.setAddress(addressInRepository);

        return ownerRepository.save(possibleNewOwner);
    }

    private boolean emailAlreadyInUse(String email)
    {
        return ownerRepository.findByEmail(email) != null;
    }

    public boolean deleteOwner(int id)
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

    public Owner getOwner(int id)
    {
        return ownerRepository.findById(id).orElse(null);
    }

    public List<Owner> getOwnerList()
    {
        return ownerRepository.findAll();
    }

    public Owner updateOwnerAddress(int ownerId, String addr)
    {
        Optional<Owner> optionalOwner = ownerRepository.findById(ownerId);

        if (!optionalOwner.isPresent())
        {
            return null;
        }

        Address newAddress = entityFactory.create(addr);
        Address addressInRepository = findOrCreateAddressInRepo(newAddress);

        Owner ownerEntity = optionalOwner.get();
        ownerEntity.setAddress(addressInRepository);

        return ownerRepository.save(ownerEntity);
    }

    private Address findOrCreateAddressInRepo(Address address)
    {
        Address addressInRepo =
                addressRepository
                        .findByHouseNumberAndPostcode(
                                address.getHouseNumber(),
                                address.getPostcode());

        if (addressInRepo == null)
        {
            addressInRepo = addressRepository.save(address);
        }

        return addressInRepo;
    }
}
