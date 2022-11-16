package com.gdmatstaffs.pets.address;

import com.gdmatstaffs.pets.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService
{
    private final AddressRepository addressRepository;

    public Address findOrCreateAddressInRepo(Address address)
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
