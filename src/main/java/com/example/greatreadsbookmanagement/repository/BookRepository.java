package com.example.greatreadsbookmanagement.repository;

import com.example.greatreadsbookmanagement.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface BookRepository extends Repository<Book,Integer> {

    @Query("SELECT DISTINCT book FROM Book book where book.title LIKE :title%")
    @Transactional(readOnly = true)
    Collection<Book> findAllBooksByTitle(@Param("title") String title);
}
