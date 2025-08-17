-- Create database if it does not exist
CREATE DATABASE IF NOT EXISTS APIDevelopSpringBoot;
USE APIDevelopSpringBoot;

-- Create users storage table
CREATE TABLE IF NOT EXISTS storage (
    book_name varchar(50),
    id varchar(50) PRIMARY KEY,
    isbn varchar(50),
    aisle varchar(50),
    author varchar(50)
);

-- Insert records to table
INSERT INTO storage (book_name, id, isbn, aisle, author) VALUES
("Microservices","hrtge43","hrtge","43","Shetty"),
("Selenium","khuys21","khuys","21","Shetty"),
("Appium","ttefs36","ttefs","36","Shetty");