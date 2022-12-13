package com.gdmatstaffs.pets.owner;

import com.gdmatstaffs.pets.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer>
{
    Owner findByEmail(String email);
    Owner findByToken(String token);
}
