CREATE TABLE IF NOT EXISTS survey_user(
id BIGINT default survey_user_seq.nextval PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL,
survey_event_id VARCHAR(50),
FOREIGN KEY (survey_event_id) REFERENCES survey_event(id)
);