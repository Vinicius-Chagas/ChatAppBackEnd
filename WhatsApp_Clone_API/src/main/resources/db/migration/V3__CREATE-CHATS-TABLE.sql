CREATE TABLE chats (
    ID BIGINT AUTO_INCREMENT NOT NULL,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    created DATE,

    PRIMARY KEY(ID),
    CONSTRAINT FK_SENDER FOREIGN KEY(sender_id) REFERENCES user(ID),
    CONSTRAINT FK_RECEIVER FOREIGN KEY(receiver_id) REFERENCES user(ID)
);

CREATE TABLE messages (
    ID BIGINT AUTO_INCREMENT NOT NULL,
    chat_id BIGINT NOT NULL,
    message TEXT,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    created DATE,

    PRIMARY KEY(ID),
    CONSTRAINT FK_MESSAGESENDER FOREIGN KEY(sender_id) REFERENCES user(ID),
    CONSTRAINT FK_MESSAGERECEIVER FOREIGN KEY(receiver_id) REFERENCES user(ID),
    CONSTRAINT FK_CHAT FOREIGN KEY(chat_id) REFERENCES chats(ID)
)