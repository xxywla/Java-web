package com.xxyw.book.service;

import com.xxyw.book.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList();
    Book getBookById(Integer bookId);
}
