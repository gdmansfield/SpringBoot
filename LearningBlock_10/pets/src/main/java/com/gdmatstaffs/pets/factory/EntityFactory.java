package com.gdmatstaffs.pets.factory;

import com.gdmatstaffs.pets.entity.Address;
import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.owner.NewOwnerDTO;
import org.springframework.stereotype.Component;

@Component
public class EntityFactory
{
    public Address create(String address)
    {
        String[] addressParts = address.split(", ");

        return new Address(
                0,
                addressParts[0],
                addressParts[1],
                addressParts[2],
                addressParts[3]);
    }

    public Owner create(NewOwnerDTO owner)
    {
        Address address = create(owner.getAddress());

        return new Owner(
                0,
                address,
                owner.getEmail(),
                owner.getName(),
                owner.getPassword(),
                owner.getTelephone(),
                null,
                null);
    }
}
