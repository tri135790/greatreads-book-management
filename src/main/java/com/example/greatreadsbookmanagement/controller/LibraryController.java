package com.example.greatreadsbookmanagement.controller;

import com.example.greatreadsbookmanagement.model.Book;
import com.example.greatreadsbookmanagement.model.BookType;
import com.example.greatreadsbookmanagement.repository.LibraryRepository;
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

    @Autowired
    private LibraryRepository libraryRepository;

    @ModelAttribute("types")
    public Collection<BookType> populateBookTypes() {
        return libraryRepository.findBookTypes();
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
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
        return "library/createOrUpdateBookForm";
    }

    @PostMapping("/library/new")
    public String processCreationForm(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "library/createOrUpdateBookForm";
        } else {
            libraryRepository.save(book);
            return "redirect:/library/" + book.getId();
        }
    }


}
