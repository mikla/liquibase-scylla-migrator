CREATE TABLE crypto.cache
(
    session_id VARCHAR,
    id         VARCHAR,
    timestamp  TIMESTAMP,
    PRIMARY KEY (session_id, id)
);
