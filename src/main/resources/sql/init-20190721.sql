-- create database
CREATE DATABASE springbootdemo DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
-- create user and grant
create user bearyang@localhost identified by '123456';
grant all privileges on springbootdemo.* to bearyang@localhost;

-- create table
CREATE TABLE EMPLOYEE (
    ID INT UNSIGNED AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    HIRE_DATE DATE,
    SALARY DECIMAL(10,2),
    DEPT_NO INT(2),
    PRIMARY KEY (ID)
);

-- insert data
INSERT INTO EMPLOYEE (NAME, HIRE_DATE, SALARY, DEPT_NO) VALUES ('小杨', '2010-09-14', 8000.0, '06');
INSERT INTO EMPLOYEE (NAME, HIRE_DATE, SALARY, DEPT_NO) VALUES ('小张', '2010-09-15', 9000.0, '05');

