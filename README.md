# 🎬 Movie Ticket Booking System - Group Project

A Java + MySQL console-based movie ticket booking system with DBMS integration.

### 👥 Group Members (3) - CSE
1. **Rakchana P** - Team Lead & Database Design
2. **Ramuna Bala T** - Backend Logic & JDBC Implementation
3. **Pushpavathi T** - Testing, GitHub & Documentation

### 📌 Work Distribution
- **Rakchana P:** Designed Database Schema (movies, bookings tables), Wrote MovieService.java with JOIN & Transaction queries
- **Ramuna Bala T:** Developed Main.java Menu System, Input validation, Booking/Cancel flow logic
- **Pushpavathi T:** Implemented DBConnection.java, Tested all features, Created GitHub Repository & Project Report

### ✨ Features
- View All Movies
- Search Movie by Title
- Book Tickets with seat availability check
- View All Bookings (JOIN query)
- Cancel Booking with automatic seat restore

### 🛠️ Tech Stack
Java, MySQL, JDBC, Git & GitHub

### 🗄️ Database Setup
CREATE DATABASE movie_booking;
USE movie_booking;
CREATE TABLE movies (id INT PRIMARY KEY, title VARCHAR(100), genre VARCHAR(50), available_seats INT);
CREATE TABLE bookings (id INT PRIMARY KEY AUTO_INCREMENT, movie_id INT, customer_name VARCHAR(100), seats_booked INT, FOREIGN KEY(movie_id) REFERENCES movies(id));
INSERT INTO movies VALUES (1,'Leo','Action',100), (2,'Jailer','Action',80), (3,'Vikram','Action',90);

### 🚀 How to Run
javac -cp "lib/mysql-connector-j-26.7.0.jar" src/*.java
java -cp "lib/mysql-connector-j-26.7.0.jar;src" Main

### 🔗 GitHub Repository
https://github.com/rakchanap2025cse-max/TicketBookingSystem

### 📸 Project Status: Completed Successfully ✅
