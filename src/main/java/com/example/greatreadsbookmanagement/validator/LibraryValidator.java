package com.example.greatreadsbookmanagement.validator;

import com.example.greatreadsbookmanagement.model.Book;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LibraryValidator implements Validator {

    private static final String REQUIRED = "required";

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        Book book = (Book) object;

        if (!StringUtils.hasLength(book.getTitle())) {
            errors.rejectValue("title", REQUIRED, REQUIRED);
        }

        if (!StringUtils.hasLength(book.getAuthor())) {
            errors.rejectValue("author", REQUIRED, REQUIRED);
        }

        if ("Currently Reading".equals(book.getShelf().getStatus())) {
            if (book.getDateAdded() == null) {
                errors.rejectValue("dateAdded", "currently_Reading_Required");
            }
        }

        if ("Read".equals(book.getShelf().getStatus())) {
            if (book.getDateAdded() == null) {
                errors.rejectValue("dateAdded", "Read_Required");
            }
            if (book.getDateFinished() == null) {
                errors.rejectValue("dateFinished", "Read_Required");
            }
        } else {
            if (book.getRating() != null) {
                errors.rejectValue("rating", "rating_available");
            }
            if (book.getDateFinished() != null) {
                errors.rejectValue("dateFinished", "date_finished_available");
            }
        }

    }
}
