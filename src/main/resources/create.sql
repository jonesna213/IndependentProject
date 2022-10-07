-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-09-16 02:44:31.569

-- tables
-- Table: household
CREATE TABLE household (
                            Id int NOT NULL AUTO_INCREMENT,
                            passwordHash varchar(255) NOT NULL,
                            salt varchar(255) NOT NULL,
                            householdName varchar(40) NOT NULL,
                            CONSTRAINT household_pk PRIMARY KEY (Id)
);

-- Table: users
CREATE TABLE user (
                       Id int NOT NULL AUTO_INCREMENT,
                       username varchar(60) NOT NULL,
                       firstName varchar(40) NOT NULL,
                       lastName varchar(60) NOT NULL,
                       email varchar(255) NULL,
                       householdPrivileges varchar(10) NOT NULL DEFAULT "user",
                       household_id int NOT NULL,
                       CONSTRAINT user_pk PRIMARY KEY (Id)
);

-- foreign keys
-- Reference: Users_Households (table: users)
ALTER TABLE user ADD CONSTRAINT User_Household FOREIGN KEY User_Household (household_id)
    REFERENCES household (Id);

-- End of file.

