DROP DATABASE cbb_db;
CREATE DATABASE `cbb_db`;
GRANT ALL PRIVILEGES ON * . * TO 'cbb_user'@'localhost';
FLUSH PRIVILEGES;
USE cbb_db;