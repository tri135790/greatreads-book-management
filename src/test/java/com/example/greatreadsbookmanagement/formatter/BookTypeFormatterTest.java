package com.example.greatreadsbookmanagement.formatter;

import com.example.greatreadsbookmanagement.model.BookType;
import com.example.greatreadsbookmanagement.repository.LibraryRepository;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ExtendWith(MockitoExtension.class)
public class BookTypeFormatterTest {

    @Mock
    private LibraryRepository libraryRepository;

    private BookTypeFormatter bookTypeFormatter;

    @BeforeEach
    void setup() {
        this.bookTypeFormatter = new BookTypeFormatter(libraryRepository);
    }

    @Test
    void testPrint() {
        BookType bookType = new BookType();
        bookType.setName("adventure");
        String bookTypeName = this.bookTypeFormatter.print(bookType, Locale.ENGLISH);
        assertThat(bookTypeName).isEqualTo("adventure");
    }

    @Test
    void testParse() throws ParseException {
        given(this.libraryRepository.findBookTypes()).willReturn(makeBookTypes());
        BookType bookType = bookTypeFormatter.parse("adventure",Locale.ENGLISH);
        assertThat(bookType.getName()).isEqualTo("adventure");
    }

    private List<BookType> makeBookTypes() {
        List<BookType> bookTypes = new ArrayList<>();
        bookTypes.add(new BookType() {
            {
                setName("adventure");
            }
        });
        bookTypes.add(new BookType() {
            {
                setName("comedy");
            }
        });
        return bookTypes;
    }


}
