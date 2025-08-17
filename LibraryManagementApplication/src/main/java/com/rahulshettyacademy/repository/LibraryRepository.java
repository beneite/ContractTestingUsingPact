package com.rahulshettyacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rahulshettyacademy.entity.LibraryEntity;
@Repository
public interface LibraryRepository extends JpaRepository<LibraryEntity, String>,LibraryRepositoryCustom{

}
