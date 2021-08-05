package com.example.greatreadsbookmanagement.formatter;

import com.example.greatreadsbookmanagement.model.Shelf;
import com.example.greatreadsbookmanagement.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class ShelfFormatter implements Formatter<Shelf> {

    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public Shelf parse(String text, Locale locale) throws ParseException {
        Collection<Shelf> findBookShelves = libraryRepository.findBookShelves();
        for (Shelf shelf : findBookShelves) {
            if (shelf.getStatus().equals(text)) {
                return shelf;
            }
        }
        throw new ParseException("book shelf not found: " + text, 0);
    }

    @Override
    public String print(Shelf shelf, Locale locale) {
        return shelf.getStatus();
    }
}
