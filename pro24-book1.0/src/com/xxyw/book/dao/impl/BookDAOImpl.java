package com.xxyw.book.dao.impl;

import com.xxyw.book.dao.BookDAO;
import com.xxyw.book.pojo.Book;
import com.xxyw.myssm.basedao.BaseDAO;

import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {

    @Override
    public List<Book> getBookList() {
        return executeQuery("select * from t_book where bookStatus = 0 ");
    }
}
