-- V1__Create_tables.sql
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP
);

CREATE TABLE messages (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          content TEXT NOT NULL,
                          timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          sender_id INT,
                          FOREIGN KEY (sender_id) REFERENCES users(id)
);