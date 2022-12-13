package com.gdmatstaffs.pets.use_case.staff;

import com.gdmatstaffs.pets.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>
{
    Staff findByEmail(String email);
    Staff findByToken(String token);
}
