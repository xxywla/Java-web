package com.xxyw.book.dao;

import com.xxyw.book.pojo.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBookList();
    Book getBookById(Integer bookId);
}
