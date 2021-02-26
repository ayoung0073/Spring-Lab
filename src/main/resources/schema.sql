DROP TABLE IF EXISTS board;

create table board (
	id BigInt not null,
		user_id BigInt not null,
    title varchar(100) not null,
    content varchar(255),
    regdate timestamp default now(),
    primary key(id),
		FOREIGN KEY (user_id) REFERENCES user(id)
    );
