package com.rahulshettyacademy.component;

import com.rahulshettyacademy.entity.LibraryEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Component
public class SpecificProductComponent {

	
	LibraryEntity product;
	@JsonInclude(Include.NON_NULL)
	String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public LibraryEntity getProduct() {
		return product;
	}
	public void setProduct(LibraryEntity product) {
		this.product = product;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@JsonInclude(Include.NON_DEFAULT)
	int price;
	@JsonInclude(Include.NON_NULL)
	String category;
	
	
}
