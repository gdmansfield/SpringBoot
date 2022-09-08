package com.gdmatstaffs.JpaRepositoryDemo.copy;

import com.gdmatstaffs.JpaRepositoryDemo.entity.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Integer>
{
}
