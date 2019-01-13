CREATE DATABASE `cbb_db`;
CREATE USER 'cbb_user'@'localhost' IDENTIFIED BY 'g/u}5?n~sA2x<fR>*(5~es(v]P}MC<fg';
GRANT ALL PRIVILEGES ON * . * TO 'cbb_user'@'%';
FLUSH PRIVILEGES;
USE `cbb_db`;