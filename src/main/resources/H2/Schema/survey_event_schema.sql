CREATE TABLE IF NOT EXISTS survey_event(
id BIGINT default survey_event_seq.nextval PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL,
description VARCHAR
);