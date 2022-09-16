-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-09-16 02:44:31.569

-- tables
-- Table: households
CREATE TABLE households (
                            Id int NOT NULL AUTO_INCREMENT,
                            passwordHash varchar(255) NOT NULL,
                            householdName varchar(40) NOT NULL,
                            CONSTRAINT households_pk PRIMARY KEY (Id)
);

-- Table: users
CREATE TABLE users (
                       Id int NOT NULL AUTO_INCREMENT,
                       username varchar(60) NOT NULL,
                       firstName varchar(40) NOT NULL,
                       lastName varchar(60) NOT NULL,
                       email varchar(255) NULL,
                       householdPrivileges varchar(10) NOT NULL DEFAULT user,
                       household int NOT NULL,
                       CONSTRAINT users_pk PRIMARY KEY (Id)
);

-- foreign keys
-- Reference: Users_Households (table: users)
ALTER TABLE users ADD CONSTRAINT Users_Households FOREIGN KEY Users_Households (household)
    REFERENCES households (Id);

-- End of file.

