-- create database
CREATE DATABASE springbootdemo DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
-- create user and grant
create user bearyang@localhost identified by '123456';
grant all privileges on springbootdemo.* to bearyang@localhost;

-- create table
CREATE TABLE EMPLOYEE (
    ID INT UNSIGNED AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    HIRE_DATE DATETIME,
    SALARY DECIMAL(10,2),
    DEPT_NO INT(2),
    PRIMARY KEY (ID)
);

-- insert data
-- 2010-09-14 00:00:00
INSERT INTO EMPLOYEE (NAME, HIRE_DATE, SALARY, DEPT_NO) VALUES ('小杨', from_unixtime(1284393600), 8000.0, '06');
-- 2010-09-15 00:00:00
INSERT INTO EMPLOYEE (NAME, HIRE_DATE, SALARY, DEPT_NO) VALUES ('小张', from_unixtime(1284480000), 9000.0, '05');

