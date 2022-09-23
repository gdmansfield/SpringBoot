package com.gdmatstaffs.JUnitDemo.copy;

import com.gdmatstaffs.JUnitDemo.entity.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Integer>
{
}
