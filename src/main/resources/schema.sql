drop table if exists account;
drop table if exists category;
drop table if exists message;
drop table if exists chat_room;
drop table if exists chatroom;
drop table if exists financial_terms;
drop table if exists memo;
drop table if exists authority;
drop table if exists user;


CREATE TABLE user (
	user_id	bigint(20)  auto_increment primary key,
	account varchar(20) not null,
	password	varchar(255) not null,
	name	varchar(20) not null,
	email	varchar(50) not null
);

CREATE TABLE authority (
    authority_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    authority_name VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE chat_room (
                          chat_room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          sender_id BIGINT NOT NULL,
                          receiver_id BIGINT NOT NULL,
                          FOREIGN KEY (sender_id) REFERENCES user(user_id),
                          FOREIGN KEY (receiver_id) REFERENCES user(user_id),
                          CHECK (sender_id <> receiver_id)
);

CREATE TABLE message (
                         message_id	BIGINT AUTO_INCREMENT PRIMARY KEY,
                         chat_room_id	BIGINT not null,
                         Content	VARCHAR(255)   not null,
                         send_time	timestamp	NULL,
                         sender_id BIGINT NOT NULL,
                         receiver_id BIGINT NOT NULL,
                         foreign key (chat_room_id) references chat_room(chat_room_id),
                         FOREIGN KEY (sender_id) REFERENCES user(user_id),
                         FOREIGN KEY (receiver_id) REFERENCES user(user_id),
                         CHECK (sender_id <> receiver_id)
);

CREATE TABLE category (
                              CATEGORY_ID   bigint(20)   NOT NULL primary key,
                              NAME   VARCHAR(255)   NULL
);

CREATE TABLE account (
                         transaction_id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         deposit INT NOT NULL,
                         withdrawal INT NOT NULL,
                         times TIMESTAMP NOT NULL,
                         balance INT NOT NULL,
                         category_id   BIGINT(20)   NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES user(user_id),
                         FOREIGN KEY(category_id) REFERENCES category(category_id)
);


CREATE TABLE financial_terms (
    term_id bigINT AUTO_INCREMENT PRIMARY KEY,
    term VARCHAR(255) NOT NULL,
    definition VARCHAR(255) NOT NULL
);

CREATE TABLE memo (
    memo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content VARCHAR(255) not null,
    created_at timestamp NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);








