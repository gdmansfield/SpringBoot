package com.gdmatstaffs.validation_demo.copy;

import com.gdmatstaffs.validation_demo.entity.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepository extends CrudRepository<Copy, Integer>
{
}
