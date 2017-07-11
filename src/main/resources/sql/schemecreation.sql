DROP DATABASE IF EXISTS `qroktask`;
CREATE DATABASE `qroktask` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE `qroktask`;

DROP TABLE IF EXISTS `Rewards`;
CREATE TABLE Rewards (
    Reward_id INT NOT NULL AUTO_INCREMENT,
    Reward_year INT NOT NULL default 2017,
    Reward_title VARCHAR(100) NOT NULL,
    PRIMARY KEY (Reward_id) );

DROP TABLE IF EXISTS `Authors`;
CREATE TABLE Authors (
    Author_id INT NOT NULL AUTO_INCREMENT,
    First_name VARCHAR(40) NOT NULL,
    Last_name VARCHAR(40) NOT NULL,
    Sex INT NOT NULL DEFAULT 0,
    Birth_date TIMESTAMP NOT NULL,
    PRIMARY KEY (Author_id) );

DROP TABLE IF EXISTS `Books`;
CREATE TABLE Books (
    Book_id INT NOT NULL AUTO_INCREMENT,
    Title VARCHAR(40) NOT NULL,
    ISBN VARCHAR(40) NOT NULL,
    Genre INT NOT NULL DEFAULT 0,
    PRIMARY KEY (Book_id) );

DROP TABLE IF EXISTS `authors_to_rewards`;
CREATE TABLE authors_to_rewards (
    Magazine_author_id INT NOT NULL,
    Magazine_reward_id INT NOT NULL,
    PRIMARY KEY (Magazine_author_id, Magazine_reward_id),
    KEY (Magazine_author_id),
    KEY (Magazine_reward_id),
    CONSTRAINT FK_REWARDMAG_1 FOREIGN KEY (Magazine_reward_id) REFERENCES Rewards (Reward_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_REWARDMAG_2 FOREIGN KEY (Magazine_author_id) REFERENCES Authors (Author_id) ON DELETE CASCADE ON UPDATE CASCADE );

DROP TABLE IF EXISTS `books_to_authors`;
CREATE TABLE books_to_authors (
    Magazine_author_id INT NOT NULL,
    Magazine_book_id INT NOT NULL,
    PRIMARY KEY (Magazine_author_id, Magazine_book_id),
    KEY (Magazine_author_id),
    KEY (Magazine_book_id),
    CONSTRAINT FK_BOOKMAG_1 FOREIGN KEY (Magazine_book_id) REFERENCES Books (Book_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_BOOKMAG_2 FOREIGN KEY (Magazine_author_id) REFERENCES Authors (Author_id) ON DELETE CASCADE ON UPDATE CASCADE );