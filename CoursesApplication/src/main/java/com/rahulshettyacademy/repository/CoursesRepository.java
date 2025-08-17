package com.rahulshettyacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahulshettyacademy.entity.AllCourseDataEntity;

public interface CoursesRepository extends JpaRepository<AllCourseDataEntity, String>{

}
