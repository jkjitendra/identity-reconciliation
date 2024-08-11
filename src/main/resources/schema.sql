CREATE TABLE Contact (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PHONE_NUMBER VARCHAR(20),
    EMAIL VARCHAR(50),
    LINKED_ID BIGINT,
    LINK_PRECEDENCE VARCHAR(10),
    CREATED_AT TIMESTAMP,
    UPDATED_AT TIMESTAMP,
    DELETED_AT TIMESTAMP,
    FOREIGN KEY (LINKED_ID) REFERENCES Contact(ID)
);