--USE `qroktask`;

insert into Rewards (Reward_year, Reward_title) values (2015, 'Reward_1');
insert into Rewards (Reward_year, Reward_title) values (2012, 'Reward_2');
insert into Rewards (Reward_year, Reward_title) values (2014, 'Reward_3');
insert into Rewards (Reward_year, Reward_title) values (2016, 'Reward_4');
insert into Rewards (Reward_year, Reward_title) values (2013, 'Reward_5');

insert into Authors (First_name, Last_name, Sex, Birth_date) values ('name1', 'surname1', 'MALE', '1980-12-31 11:30:45');
insert into Authors (First_name, Last_name, Sex, Birth_date) values ('name2', 'surname2', 'FEMALE', '1989-11-12 11:30:45');
insert into Authors (First_name, Last_name, Sex, Birth_date) values ('name3', 'surname3', 'OTHER', '1975-09-25 11:30:45');
insert into Authors (First_name, Last_name, Sex, Birth_date) values ('name4', 'surname4', 'MALE', '1995-07-11 11:30:45');
insert into Authors (First_name, Last_name, Sex, Birth_date) values ('name5', 'surname5', 'FEMALE', '1991-01-02 11:30:45');

insert into Books (Title, ISBN, Genre) values ('book1', '8712983471', 'COMEDY');
insert into Books (Title, ISBN, Genre) values ('book2', '4526534266', 'DRAMA');
insert into Books (Title, ISBN, Genre) values ('book3', '6574674565', 'HORROR');
insert into Books (Title, ISBN, Genre) values ('book4', '1352345234', 'SCIFI');
insert into Books (Title, ISBN, Genre) values ('book5', '8545645657', 'ACTION');

insert into authors_to_rewards (Magazine_author_id, Magazine_reward_id) values (1, 1);
insert into authors_to_rewards (Magazine_author_id, Magazine_reward_id) values (1, 2);
insert into authors_to_rewards (Magazine_author_id, Magazine_reward_id) values (2, 3);
insert into authors_to_rewards (Magazine_author_id, Magazine_reward_id) values (3, 1);
insert into authors_to_rewards (Magazine_author_id, Magazine_reward_id) values (3, 4);
insert into authors_to_rewards (Magazine_author_id, Magazine_reward_id) values (3, 5);
insert into authors_to_rewards (Magazine_author_id, Magazine_reward_id) values (5, 4);
insert into authors_to_rewards (Magazine_author_id, Magazine_reward_id) values (5, 2);

insert into books_to_authors (Magazine_author_id, Magazine_book_id) values (1, 1);
insert into books_to_authors (Magazine_author_id, Magazine_book_id) values (2, 2);
insert into books_to_authors (Magazine_author_id, Magazine_book_id) values (3, 3);
insert into books_to_authors (Magazine_author_id, Magazine_book_id) values (4, 4);
insert into books_to_authors (Magazine_author_id, Magazine_book_id) values (5, 5);