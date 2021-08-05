package com.example.greatreadsbookmanagement.formatter;

import com.example.greatreadsbookmanagement.model.Book;
import com.example.greatreadsbookmanagement.model.BookType;
import com.example.greatreadsbookmanagement.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class BookTypeFormatter implements Formatter<BookType> {

    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public BookType parse(String text, Locale locale) throws ParseException {
        Collection<BookType> findBookTypes = libraryRepository.findBookTypes();
        for (BookType bookType : findBookTypes) {
            if (bookType.getName().equals(text)) {
                return bookType;
            }
        }
        throw new ParseException("book type not found: " + text, 0);
    }

    @Override
    public String print(BookType bookType, Locale locale) {
        return bookType.getName();
    }
}
