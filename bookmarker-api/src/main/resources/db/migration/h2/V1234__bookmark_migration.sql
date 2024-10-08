-- Create the sequence
CREATE SEQUENCE bm_id_seq
    START WITH 1
    INCREMENT BY 50;

-- Create the table
CREATE TABLE bookmarks (
                           id BIGINT DEFAULT nextval('bm_id_seq') NOT NULL,
                           title VARCHAR(255) NOT NULL,
                           url VARCHAR(255) NOT NULL,
                           created_at TIMESTAMP(6) WITH TIME ZONE DEFAULT now(),
                           PRIMARY KEY (id)
);
