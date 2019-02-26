CREATE DATABASE hi_game
CHARACTER SET UTF8
COLLATE 'utf8_general_ci';

USE hi_game;


-- Before MySQL 5.6, you may be used trigger for updating field update_time
CREATE TABLE t_account (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    nickname VARCHAR(20) NOT NULL,
    create_time TIMESTAMP DEFAULT NOW(),
    -- MySQL 5.7
    update_time TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_vegetables (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO t_vegetables(name) VALUES('carrot');
INSERT INTO t_vegetables(name) VALUES('radish');
INSERT INTO t_vegetables(name) VALUES('tomato');
INSERT INTO t_vegetables(name) VALUES('potato');

CREATE TABLE t_grow (
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL,
    vegetables_id INT NOT NULL,
    level INT DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

