package com.xxyw.book.service.impl;

import com.xxyw.book.dao.BookDAO;
import com.xxyw.book.pojo.Book;
import com.xxyw.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    BookDAO bookDAO;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookDAO.getBookById(bookId);
    }
}
