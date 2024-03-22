package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Orders {

	@Id
	private int orderid;
	
	@ManyToOne
	@JoinColumn(name="id", insertable=true, updatable=true)
	private Member member;
	   
	@ManyToOne
	@JoinColumn(name="bookid", insertable=true, updatable=true)
	private Book book;
	
	private int saleprice;
	private String orderdate;
}
