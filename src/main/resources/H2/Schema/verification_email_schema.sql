DROP TABLE IF EXISTS verification_email;
CREATE TABLE verification_email(
email VARCHAR(50) PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL,
verification_code INT
);