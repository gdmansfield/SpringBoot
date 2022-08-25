package com.gdmatstaffs.CrudRepositoryDemo.copy_status;

import com.gdmatstaffs.CrudRepositoryDemo.entity.CopyStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyStatusRepository extends CrudRepository<CopyStatus, Integer>
{
}
