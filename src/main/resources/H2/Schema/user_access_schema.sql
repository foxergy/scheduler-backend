CREATE TABLE IF NOT EXISTS user_access(
id BIGINT default user_access_seq.nextval PRIMARY KEY NOT NULL,
username VARCHAR(50) NOT NULL UNIQUE,
email VARCHAR(100) NOT NULL,
password VARCHAR(100) NOT NULL
);