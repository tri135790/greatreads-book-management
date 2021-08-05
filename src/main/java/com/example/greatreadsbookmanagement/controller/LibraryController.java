package com.example.greatreadsbookmanagement.controller;

import com.example.greatreadsbookmanagement.model.Book;
import com.example.greatreadsbookmanagement.model.BookType;
import com.example.greatreadsbookmanagement.model.Shelf;
import com.example.greatreadsbookmanagement.repository.LibraryRepository;
import com.example.greatreadsbookmanagement.validator.LibraryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class LibraryController {

    private final static String BOOK_CREATE_OR_UPDATE_FORM = "library/createOrUpdateBookForm";

    @Autowired
    private LibraryRepository libraryRepository;

    @ModelAttribute("types")
    public Collection<BookType> populateBookTypes() {
        return libraryRepository.findBookTypes();
    }

    @ModelAttribute("shelves")
    public Collection<Shelf> populateBookShelves() {
        return libraryRepository.findBookShelves();
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder
    public void initLibraryBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new LibraryValidator());
    }

    @GetMapping("/library/find")
    public String initFindForm(Model model) {
        model.addAttribute("book", new Book());
        return "library/findBooks";
    }

    @GetMapping("/library")
    public String processFindForm(@ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {

        if (book.getTitle() == null) {
            book.setTitle("");
        }

        Collection<Book> results = libraryRepository.findBooksInLibraryByTitle(book.getTitle());

        if (results.isEmpty()) {
            bindingResult.rejectValue("title", "notFound", "not found");
            return "library/findBooks";
        } else if (results.size() == 1) {
            book = results.iterator().next();
            return "redirect:/library/" + book.getId();
        } else {
            model.addAttribute("bookslist", results);
            return "library/booksList";
        }
    }

    @GetMapping("/library/{bookId}")
    public String showBook(@PathVariable("bookId") Integer bookId,Model model) {
        Book book = libraryRepository.findById(bookId);
        model.addAttribute("book", book);
        return "library/bookDetails";
    }

    @GetMapping("/library/new")
    public String initCreationForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return BOOK_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/library/new")
    public String processCreationForm(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return BOOK_CREATE_OR_UPDATE_FORM;
        } else {
            libraryRepository.save(book);
            return "redirect:/library/" + book.getId();
        }
    }

    @GetMapping("/library/{bookId}/edit")
    public String initUpdateBookForm(@PathVariable("bookId") int bookId, Model model) {
        Book book = libraryRepository.findById(bookId);
        model.addAttribute("book", book);
        return BOOK_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/library/{bookId}/edit")
    public String processUpdateForm(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, @PathVariable("bookId") int bookId) {
        if (bindingResult.hasErrors()) {
            return BOOK_CREATE_OR_UPDATE_FORM;
        } else {
            book.setId(bookId);
            libraryRepository.save(book);
            return "redirect:/library/{bookId}";
        }
    }


}
