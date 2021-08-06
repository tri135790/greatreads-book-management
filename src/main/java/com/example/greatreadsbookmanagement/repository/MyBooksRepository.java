package com.example.greatreadsbookmanagement.repository;

import com.example.greatreadsbookmanagement.model.Book;
import com.example.greatreadsbookmanagement.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface MyBooksRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT DISTINCT book from Book book where book.shelf.status <> 'None' ORDER BY book.shelf.status")
    @Transactional(readOnly = true)
    Collection<Book> findAllMyBooks();

    @Query("SELECT DISTINCT book from Book book where book.shelf.status =:shelf ")
    @Transactional(readOnly = true)
    Collection<Book> findMyBooksByShelf(@Param("shelf") String shelf);

    @Query("SELECT DISTINCT shelf from Shelf shelf where shelf.status = 'None'")
    @Transactional(readOnly = true)
    Shelf findNoneShelf();

}
