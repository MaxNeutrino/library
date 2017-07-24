DROP TABLE IF EXISTS books;

CREATE TABLE IF NOT EXISTS books
(
  id   INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL
);

INSERT INTO books (id, name) VALUES
  (1, 'one'),
  (2, 'two'),
  (3, 'three'),
  (4, 'threeName');