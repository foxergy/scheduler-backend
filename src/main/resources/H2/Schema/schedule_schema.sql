CREATE TABLE IF NOT EXISTS schedule(
id BIGINT default schedule_seq.nextval PRIMARY KEY NOT NULL,
start_time TIMESTAMP NOT NULL,
end_time TIMESTAMP NOT NULL,
survey_event_id VARCHAR(50) NOT NULL,
survey_user_id BIGINT,
FOREIGN KEY (survey_event_id) REFERENCES survey_event(id),
FOREIGN KEY (survey_user_id) REFERENCES survey_user(id)
);