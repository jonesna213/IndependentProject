-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-10-14 23:06:20.955
ALTER TABLE user
    DROP FOREIGN KEY User_Households;

ALTER TABLE bills
    DROP FOREIGN KEY bills_households;

ALTER TABLE chores
    DROP FOREIGN KEY chores_households;

-- tables
DROP TABLE bills;

DROP TABLE chores;

DROP TABLE households;

DROP TABLE user;

-- tables
-- Table: bills
CREATE TABLE bills (
                       id int NOT NULL AUTO_INCREMENT,
                       title varchar(100) NOT NULL,
                       description varchar(255) NULL,
                       dueDate varchar(20) NOT NULL,
                       amount varchar(20) NOT NULL,
                       frequency varchar(10) NOT NULL,
                       household_Id int NOT NULL,
                       CONSTRAINT bills_pk PRIMARY KEY (id)
);

-- Table: chores
CREATE TABLE chores (
                        id int NOT NULL AUTO_INCREMENT,
                        name varchar(255) NOT NULL,
                        description varchar(255) NULL,
                        completeBy varchar(100) NOT NULL,
                        frequency varchar(10) NOT NULL,
                        household_Id int NOT NULL,
                        CONSTRAINT chores_pk PRIMARY KEY (id)
);

-- Table: households
CREATE TABLE households (
                            Id int NOT NULL AUTO_INCREMENT,
                            passwordHash varchar(255) NOT NULL,
                            salt varchar(255) NOT NULL,
                            householdName varchar(40) NOT NULL,
                            CONSTRAINT households_pk PRIMARY KEY (Id)
);

-- Table: user
CREATE TABLE user (
                       Id int NOT NULL AUTO_INCREMENT,
                       username varchar(60) NOT NULL,
                       firstName varchar(40) NOT NULL,
                       lastName varchar(60) NOT NULL,
                       email varchar(255) NULL,
                       householdPrivileges varchar(10) NOT NULL DEFAULT 'user',
                       household_id int NOT NULL,
                       CONSTRAINT users_pk PRIMARY KEY (Id)
);

-- foreign keys
-- Reference: Users_Households (table: users)
ALTER TABLE user ADD CONSTRAINT User_Households FOREIGN KEY User_Households (household_id)
    REFERENCES households (Id);

-- Reference: bills_households (table: bills)
ALTER TABLE bills ADD CONSTRAINT bills_households FOREIGN KEY bills_households (household_Id)
    REFERENCES households (Id);

-- Reference: chores_households (table: chores)
ALTER TABLE chores ADD CONSTRAINT chores_households FOREIGN KEY chores_households (household_Id)
    REFERENCES households (Id);

-- End of file.

