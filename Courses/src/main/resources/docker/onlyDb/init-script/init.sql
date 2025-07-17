-- Create database if it does not exist
CREATE DATABASE IF NOT EXISTS Courses;
USE Courses;

-- Create users storage table
CREATE TABLE IF NOT EXISTS course (
    course_name varchar(50) PRIMARY KEY,
    id varchar(50),
    price int,
    category varchar(50)
);

-- Insert records to table
INSERT INTO course (course_name, id, price, category) VALUES
("Microservices testing","2",23,"api"),
("Selenium","3",66,"web"),
("Appium","12",13,"mobile");