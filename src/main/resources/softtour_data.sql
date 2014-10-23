USE softtour;

SET NAMES 'utf8';
SET NAMES utf8;
SET NAMES utf8 COLLATE utf8_general_ci;
SET CHARACTER SET utf8;

INSERT INTO role VALUES
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_ANONYMOUS'),
  (3, 'ROLE_USER');

INSERT INTO user VALUES
  (1, 'Ronaldo', 'cristiano@gmail.com', 'c519e76f6719da3d089433d16ec68662b80087361af1253b0f1ca12fa4da9781', '1985-05-02', 'MALE', '+380506007733', 3, true),
  (2, 'Messi', 'lionel@gmail.com', '7b443e7a5d6f7b0b3b700b540cd46338be7808dfc815acf83a9e80111c4bdd97', '1987-06-24', 'MALE', '+380994455666', 3, true),
  (3, 'Masha', 'kisa@gmail.com', '7b443e7a5d6f7b0b3b700b540cd46338be7808dfc815acf83a9e80111c4bdd97', '1987-06-24', 'FEMALE', '+380994455666', 3, true);

INSERT INTO tour VALUES
  (1, '2014-09-14', 6, '11:30:00', 'Київ', 242.00, 3, 2,'APART', 'HB', 1),
  (2, '2014-09-19', 10, '16:00:00', 'Львів', 350.00, 4, 2,'UNKNOWN', 'BB', 2),
  (3, '2014-09-24', 6, '14:30:00', 'Київ', 750.00, 3, 0,'STD', 'HB', 4),
  (4, '2014-09-26', 6, '20:00:00', 'Львів', 450.00, 4, 2,'DELUXE_SUPERIOR', 'FB', 2),
  (5, '2014-10-01', 6, '17:30:00', 'Київ', 600.00, 1, 0,'STD', 'AI', 5),
  (6, '2014-10-08', 6, '15:00:00', 'Львів', 250.00, 2, 2,'SUPERIOR','UAI', 3);

INSERT INTO favorite VALUES
  (1, '2014-09-04', 1, 1),
  (2, '2014-09-04', 2, 1),
  (3, '2014-09-04', 1, 2),
  (4, '2014-09-04', 2, 2),
  (5, '2014-09-04', 1, 3),
  (6, '2014-09-04', 2, 3);

INSERT INTO feedback VALUES
  (1, 4, 3, 4, 5, 'Not bed', 2, 2),
  (2, 5, 4, 5, 4, 'NULL', 3, 1),
  (3, 5, 4, 5, 4, 'bla bla bla bla bla bla bla bla ', 14, 2),
  (4, 5, 4, 5, 4, 'fgdgjdg ', 14, 3),
  (5, 5, 4, 5, 4, 'SPAM SPAM SPAM SPAM SPAM SPAM SPAM SPAM SPAM SPAM SPAM SPAM SPAM SPAM SPAM SPAM', 14, 2);

INSERT INTO historyrecords VALUES
  (1, '2014-09-10', 1, 1),
  (2, '2014-09-01', 2, 1),
  (3, '2014-09-04', 1, 2),
  (4, '2014-08-05', 2, 2),
  (5, '2014-09-01', 1, 3),
  (6, '2014-09-01', 2, 3),
  (7, '2014-09-01', 1, 4),
  (8, '2014-09-01', 2, 4),
  (9, '2014-09-01', 1, 5),
  (10, '2014-09-01', 2, 5);

INSERT INTO historyrequest VALUES
  (1, '2014-09-08', '2014-09-11', 6, 14, '5', 1, 0, 200.00, 550.00, 'Львів', 1, 1, '2014-09-04'),
  (2, '2014-09-09', '2014-09-14', 7, 16, '4,5', 1, 0, 150.00, 400.00, 'Київ', 2, 1, '2014-09-04');