package com.dgut.mvc.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.dgut.mvc.bean.Book;
import com.dgut.mvc.bean.BookSearchCondition;
import com.dgut.mvc.dao.BookDao;
import com.dgut.mvc.daoImpl.BookDaoJdbcImpl;
import com.jdbc.dgut.mvc.utils.jdbcTools;

public class BookService {
	private BookDao dao;

	public BookService() {
		super();
		this.dao = new BookDaoJdbcImpl();
	}

	//�ύ�����鼮��Ϣ
	public void uploadBook(Book book) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			connection.setAutoCommit(false);
			dao.addBook(connection, book);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}

	// ͨ��id��ѯ�����鼮��Ϣ
	public Book searchBook(int id) {
		Connection connection = null;
		Book book = null;
		try {
			connection = jdbcTools.getConnection();
			book = dao.searchBook(connection, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return book;
	}

	// �޸Ķ����鼮��Ϣ
	public void editBook(Book book) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			connection.setAutoCommit(false);
			dao.updateBook(connection, book);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}

	// ���������ѷ����Ķ����鼮��Ϣ
	public List<Book> getAllBooks() {
		Connection connection = null;
		List<Book> books = new ArrayList<Book>();
		try {
			connection = jdbcTools.getConnection();
			books = dao.fetchPostedAllBook(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return books;
	}

	// �������û����ύ�򷢲��Ķ����鼮��Ϣ
	public List<Book> getUserAllBooks(Integer uid) {
		Connection connection = null;
		List<Book> books = new ArrayList<Book>();
		try {
			connection = jdbcTools.getConnection();
			books = dao.fetchUserAllBook(connection, uid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return books;
	}

	// �������û�δ�����Ķ����鼮��Ϣ
	public List<Book> getUserUnpostBooks(Integer uid) {
		Connection connection = null;
		List<Book> books = new ArrayList<Book>();
		try {
			connection = jdbcTools.getConnection();
			books = dao.fetchUserUnpostBook(connection, uid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return books;
	}

	// ͨ���鼮��Ϣģ������
	public List<Book> SearchBooksByCondition(BookSearchCondition conditions) {
		Connection connection = null;
		List<Book> books = new ArrayList<Book>();
		try {
			connection = jdbcTools.getConnection();
			books = dao.searchBooksByCondition(connection, conditions);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return books;
	}

	// �����鼮������Ϣ
	public void PostBook(String id) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			connection.setAutoCommit(false);
			dao.setBookIsPost(connection, id, true);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}

	// �����鼮������Ϣ
	public void revokeBook(String id) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			connection.setAutoCommit(false);
			dao.setBookIsPost(connection, id, false);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}
	
	//
	public void changeBookCommentRight(Book book) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			connection.setAutoCommit(false);
			if(book.getIsComment()==0)book.setIsComment(1);
			else book.setIsComment(0);
			dao.updateBook(connection, book);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}
	
	
}
