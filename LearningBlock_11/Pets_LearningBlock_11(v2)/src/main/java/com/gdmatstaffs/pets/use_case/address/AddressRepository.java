package com.gdmatstaffs.pets.use_case.address;

import com.gdmatstaffs.pets.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>
{
    Address findByHouseNumberAndPostcode(String houseNumber, String postcode);
}
