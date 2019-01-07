CREATE DATABASE `cbb_db`;
CREATE USER 'cbb_user'@'localhost' IDENTIFIED BY 'cbb_password';
GRANT ALL PRIVILEGES ON * . * TO 'cbb_user'@'localhost';
FLUSH PRIVILEGES;
USE `cbb_db`;