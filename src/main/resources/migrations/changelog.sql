-- liquibase formatted sql

-- changeset my_name:1
CREATE TABLE test_table
(
    test_id     INT,
    test_column INT,
    PRIMARY KEY (test_id)
)