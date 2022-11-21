DROP DATABASE IF EXISTS guessNumDB;
CREATE DATABASE guessNumDB;
USE guessNumDB;
CREATE TABLE Games(
    id int PRIMARY KEY AUTO_INCREMENT,
    answer varchar(50) NOT NULL,
    isFinished BOOLEAN NOT NULL DEFAULT 0
);
CREATE TABLE Rounds (
    id int PRIMARY KEY AUTO_INCREMENT,
    gameID int,
    Timestamp timeStamp,
    guess varchar(10),
    guessResult VarcHAR(50)
);
ALTER TABLE Rounds
    ADD CONSTRAINT fk_round_game
        FOREIGN KEY (gameID)
        REFERENCES Games(id);
DROP DATABASE IF EXISTS guessGame_tests;
CREATE DATABASE guessGame_tests;
USE guessGame_tests;
CREATE TABLE Games(
    id int PRIMARY KEY AUTO_INCREMENT,
    answer varchar(50) NOT NULL,
    isFinished BOOLEAN NOT NULL DEFAULT 0
);
CREATE TABLE Rounds(
    id int PRIMARY KEY AUTO_INCREMENT,
    gameID int,
    Timestamp timeStamp,
    guess varchar(10),
    guessResult VarcHAR(50)
);
ALTER TABLE Rounds
    ADD CONSTRAINT fk_round_game
        FOREIGN KEY (gameID)
        REFERENCES Games(id);