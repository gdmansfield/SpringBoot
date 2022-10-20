package com.gdmatstaffs.validation_demo.copy_status;

import com.gdmatstaffs.validation_demo.entity.CopyStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyStatusRepository extends CrudRepository<CopyStatus, Integer>
{
}
