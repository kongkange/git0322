package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Book;

public interface BookDAO extends JpaRepository<Book, Integer> {

	@Query(value = "select ifnull(max(no),0)+1 from book", nativeQuery = true)
	public int getNextNo();
}
