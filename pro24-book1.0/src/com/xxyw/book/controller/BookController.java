package com.xxyw.book.controller;

import com.xxyw.book.pojo.Book;
import com.xxyw.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {
    BookService bookService;

    public String index(HttpSession session) {
        List<Book> bookList = bookService.getBookList();

        session.setAttribute("bookList", bookList);

        return "index";
    }
}
