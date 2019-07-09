CREATE TABLE IF NOT EXISTS survey_event(
id VARCHAR(50) PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL,
description VARCHAR,
username VARCHAR,
FOREIGN KEY (username) REFERENCES user_access(username)
);