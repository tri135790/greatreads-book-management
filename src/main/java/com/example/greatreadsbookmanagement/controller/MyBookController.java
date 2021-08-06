package com.example.greatreadsbookmanagement.controller;

import com.example.greatreadsbookmanagement.constant.Constant;
import com.example.greatreadsbookmanagement.model.Book;
import com.example.greatreadsbookmanagement.model.Shelf;
import com.example.greatreadsbookmanagement.repository.MyBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class MyBookController {

    @Autowired
    MyBooksRepository myBooksRepository;

    @GetMapping("/myBooks")
    public String initAllMyBooksList(Model model) {
        Collection<Book> allMyBooksList= myBooksRepository.findAllMyBooks();
        model.addAttribute("bookslist", allMyBooksList);
        return "myBooks/booksList";
    }

    @GetMapping("/myBooks/{shelf}")
    public String initMyBooksListByShelf(@PathVariable("shelf") String shelf, Model model) {
        String shelfType = null;
        switch (shelf) {
            case "want-to-read" :
                shelfType = Constant.SHELF_STATUS_WANT_TO_READ;
                break;
            case "currently-reading" :
                shelfType = Constant.SHELF_STATUS_CURRENTLY_READING;
                break;
            case "read" :
                shelfType = Constant.SHELF_STATUS_READ;
                break;
        }
        Collection<Book> myBooksByShelf = myBooksRepository.findMyBooksByShelf(shelfType);
        model.addAttribute("bookslist", myBooksByShelf);
        return "myBooks/booksList";
    }

    @GetMapping("/myBooks/remove")
    public String removeBookFromMyBooks(@RequestParam("bookId") int bookId) {
        Book book = myBooksRepository.getById(bookId);
        book.setShelf(myBooksRepository.findNoneShelf());
        book.setDateAdded(null);
        book.setDateFinished(null);
        book.setRating(null);
        myBooksRepository.save(book);
        return"redirect:/myBooks";
    }


}
