DROP TABLE user;

CREATE TABLE user (
    full_name VARCHAR(150),
    created DATE,
    phone_number VARCHAR(13) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    ID BIGINT auto_increment NOT NULL,

    PRIMARY KEY(ID)
)