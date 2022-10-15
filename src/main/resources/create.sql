-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-10-14 23:06:20.955
ALTER TABLE user
    DROP FOREIGN KEY user_households;

ALTER TABLE chores
    DROP FOREIGN KEY chores_households;

-- tables
DROP TABLE chores;

DROP TABLE households;

DROP TABLE user;

-- tables
-- Table: chores
CREATE TABLE chores (
                        id int NOT NULL AUTO_INCREMENT,
                        name varchar(255) NOT NULL,
                        description text NOT NULL,
                        completeBy varchar(100) NOT NULL,
                        frequency varchar(10) NOT NULL,
                        household_Id int NOT NULL,
                        CONSTRAINT chores_pk PRIMARY KEY (id)
);

-- Table: households
CREATE TABLE households (
                            id int NOT NULL AUTO_INCREMENT,
                            passwordHash varchar(255) NOT NULL,
                            salt varchar(255) NOT NULL,
                            householdName varchar(40) NOT NULL,
                            CONSTRAINT households_pk PRIMARY KEY (Id)
);

-- Table: users
CREATE TABLE user (
                       id int NOT NULL AUTO_INCREMENT,
                       username varchar(60) NOT NULL,
                       firstName varchar(40) NOT NULL,
                       lastName varchar(60) NOT NULL,
                       email varchar(255) NULL,
                       householdPrivileges varchar(10) NOT NULL DEFAULT 'user',
                       household_Id int NOT NULL,
                       CONSTRAINT user_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: User_Households (table: user)
ALTER TABLE user ADD CONSTRAINT user_Households FOREIGN KEY user_households (household_Id)
    REFERENCES households (id);

-- Reference: chores_households (table: chores)
ALTER TABLE chores ADD CONSTRAINT chores_households FOREIGN KEY chores_households (household_Id)
    REFERENCES households (id);

-- End of file.

