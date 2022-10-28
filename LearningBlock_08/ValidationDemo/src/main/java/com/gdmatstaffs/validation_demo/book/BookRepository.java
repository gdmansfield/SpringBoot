package com.gdmatstaffs.validation_demo.book;

import com.gdmatstaffs.validation_demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{
}
