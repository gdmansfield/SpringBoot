package com.gdmatstaffs.JUnitDemo.book;

import com.gdmatstaffs.JUnitDemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{
}
