package com.gdmatstaffs.JpaRepositoryDemo.copy_status;

import com.gdmatstaffs.JpaRepositoryDemo.entity.CopyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyStatusRepository extends JpaRepository<CopyStatus, Integer>
{
}
