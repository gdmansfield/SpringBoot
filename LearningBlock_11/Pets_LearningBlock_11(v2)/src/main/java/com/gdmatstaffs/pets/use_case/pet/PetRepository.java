package com.gdmatstaffs.pets.use_case.pet;

import com.gdmatstaffs.pets.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>
{
}
