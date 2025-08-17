package com.rahulshettyacademy.repository;

import java.util.List;

import com.rahulshettyacademy.entity.LibraryEntity;

public interface LibraryRepositoryCustom {
	
	List<LibraryEntity> findAllByAuthor(String authorName);

	LibraryEntity findByName(String bookName);

}
