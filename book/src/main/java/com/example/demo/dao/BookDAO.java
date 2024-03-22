package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Book;

public interface BookDAO extends JpaRepository<Book, Integer> {

	@Query(value = "select ifnull(max(bookid),0)+1 from book", nativeQuery = true)
	public int getNextBookid();

	@Query(value = "select * from book where ?1 like '%?2%'", nativeQuery = true)
	public List<Book> selectBook(String category, String keyword);
}
