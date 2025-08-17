package com.rahulshettyacademy.repository;

import java.util.ArrayList;
import java.util.List;

import com.rahulshettyacademy.entity.LibraryEntity;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom {

    LibraryRepository repository;

    @Override
    public List<LibraryEntity> findAllByAuthor(String authorName) {
        List<LibraryEntity> bookswithAuthor = new ArrayList<LibraryEntity>();
        // TODO Auto-generated method stub
        List<LibraryEntity> books = repository.findAll();
        for (LibraryEntity item : books)
//			{
	if(item.getAuthor().equalsIgnoreCase(authorName))
	{
		bookswithAuthor.add(item);
	}
//			}

        return bookswithAuthor;
    }

    @Override
    public LibraryEntity findByName(String bookName) {
        List<LibraryEntity> bookswithAuthor = new ArrayList<LibraryEntity>();
        // TODO Auto-generated method stub
        List<LibraryEntity> books = repository.findAll();
        for (LibraryEntity item : books)
//			{
            if (item.getBook_name().equalsIgnoreCase(bookName)) {
                return item;
            }
//			}
        return null;


    }


}
