package com.gdmatstaffs.CrudRepositoryDemo.book;

import com.gdmatstaffs.CrudRepositoryDemo.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/*
 * By Inversion of Control, the IoC container generates
 * an instance of this class and performs dependency
 * injection when needed (see BookService class)
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Integer>
{
}
