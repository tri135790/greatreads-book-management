package com.example.greatreadsbookmanagement.controller;

import com.example.greatreadsbookmanagement.model.Book;
import com.example.greatreadsbookmanagement.model.BookType;
import com.example.greatreadsbookmanagement.model.Shelf;
import com.example.greatreadsbookmanagement.repository.LibraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

@WebMvcTest(LibraryController.class)
public class LibraryControllerTests {

    private static final int TEST_BOOK_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryRepository libraryRepository;

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book();
        testBook.setId(TEST_BOOK_ID);
        testBook.setTitle("Tu Do");
        testBook.setAuthor("Phan Manh Quynh");
        testBook.setRating(5);
        testBook.setDateAdded(LocalDate.now());
        testBook.setDateFinished(LocalDate.now());
        BookType bookType = new BookType();
        bookType.setName("adventure");
        testBook.setBookType(bookType);
        Shelf shelf = new Shelf();
        shelf.setStatus("Read");
        testBook.setShelf(shelf);
        given(this.libraryRepository.findById(TEST_BOOK_ID)).willReturn(testBook);
    }

    @Test
    void testInitFindForm() throws Exception {
        mockMvc.perform(get("/library/find"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("library/findBooks"));
    }

    @Test
    void testProcessFindFormSuccess() throws Exception {
    }

}
