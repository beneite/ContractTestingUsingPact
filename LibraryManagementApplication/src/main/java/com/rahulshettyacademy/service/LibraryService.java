package com.rahulshettyacademy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahulshettyacademy.entity.LibraryEntity;
import com.rahulshettyacademy.repository.LibraryRepository;
@Service
public class LibraryService {

	@Autowired
	LibraryRepository repository;
	public String buildId(String isbn,int aisle)
	{
		if(isbn.startsWith("Z"))
		{
			return "OLD"+isbn+aisle;
		}
		
		return isbn+aisle;
	}
	public boolean checkBookAlreadyExist(String id)
	{
		Optional<LibraryEntity> lib=repository.findById(id);
		if(lib.isPresent())
			return true;
		else
			return false;
		
		
	}
	public LibraryEntity getBookById(String id)
	{
		return repository.findById(id).get();
	}
}
