package com.example.greatreadsbookmanagement.repository;

import com.example.greatreadsbookmanagement.model.Book;
import com.example.greatreadsbookmanagement.model.BookType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface LibraryRepository extends Repository<Book,Integer> {

    @Query("SELECT DISTINCT book FROM Book book where book.title LIKE :title%")
    @Transactional(readOnly = true)
    Collection<Book> findBooksInLibraryByTitle(@Param("title") String title);

    @Query("SELECT book FROM Book book WHERE book.id =:id")
    @Transactional(readOnly = true)
    Book findById(@Param("id") Integer id);

    @Query("SELECT booktype from BookType booktype ORDER BY booktype.name")
    @Transactional(readOnly = true)
    List<BookType> findBookTypes();

    void save(Book book);
}
