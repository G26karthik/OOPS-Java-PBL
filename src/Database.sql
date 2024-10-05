CREATE DATABASE mental_health_db;
USE mental_health_db;

CREATE TABLE responses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    depression_score INT,
    anxiety_score INT,
    stress_score INT,
    submission_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE USER 'your_mysql_username'@'localhost' IDENTIFIED BY 'your_mysql_password';
GRANT ALL PRIVILEGES ON mental_health_db.* TO 'your_mysql_username'@'localhost';
FLUSH PRIVILEGES;
