package com.gdmatstaffs.CrudRepositoryDemo.copy;

import com.gdmatstaffs.CrudRepositoryDemo.entity.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CopyRepository extends CrudRepository<Copy, Integer>
{
    int countByBookId(int bookId);
    Stream<Copy> streamAllByBookId(int bookId);
}
