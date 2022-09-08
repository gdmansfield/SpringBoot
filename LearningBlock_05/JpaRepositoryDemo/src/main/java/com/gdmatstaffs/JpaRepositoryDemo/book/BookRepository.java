package com.gdmatstaffs.JpaRepositoryDemo.book;

import com.gdmatstaffs.JpaRepositoryDemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{
}
