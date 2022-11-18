DELETE FROM user;
DELETE FROM households;
INSERT INTO households VALUES (1, 'password', 'salt', 'testHousehold');
INSERT INTO user VALUES (1, 'bobbyjoe', 'Bobby', 'Joe', 'bobbyjoe@gmail.com', 'user', 1);
INSERT INTO user VALUES (2, 'joeBob', 'Joe', 'Bob', 'joebob@gmail.com', 'user', 1);
INSERT INTO user VALUES (3, 'jeffJames', 'Jeff', 'James', 'jeffjames@gmail.com', 'user', 1);