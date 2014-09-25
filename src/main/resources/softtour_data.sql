USE softtour;

INSERT INTO role VALUES
 (1,'admin'),
 (2,'ordinaryUser'),
 (3,'registeredUser');

INSERT INTO user VALUES
 (1,'Andriy','elips@gmail.com','1111','1987-11-24',26,'MALE','+38(095)800-08-77',3),
 (2,'Taras','taras@gmail.com','2222','1990-06-12',24,'MALE','+38(099)445-56-66',3);

INSERT INTO country VALUES
  (1,'Egypt'),
  (2,'Bulgaria'),
  (3,'Greece'),
  (4,'Turkey');

INSERT INTO region VALUES
  (1,'Antalya',4),
  (2,'Sharm El Sheikh',1),
  (3,'Athens',3);

INSERT INTO hotel VALUES
  (1,'Antalya',5, 1, 3.2, 4.6, 2.3, 5.0, 4.2),
  (2,'Viking Club',4, 2, 3.8, 4.0, 4.7, 3.6, 4.2),
  (3,'Amarilia',5, 3, 3.2, 4.6, 5.0, 4.0, 4.7);

INSERT INTO food VALUES
  (1,'HB'),
  (2,'BB'),
  (3,'FB'),
  (4,'AI'),
  (5,'UAI'),
  (6,'RO');

INSERT INTO tour VALUES
 (1,'2014-09-14',6,'11:30:00','kyiv',242.00,3,4),
 (2,'2014-09-19',10,'16:00:00','lviv',350.00,2,3);

INSERT INTO favorite VALUES
  (1,'2014-09-04',2,2),
  (2,'2014-09-04',1,1),
  (3,'2014-09-04',1,2),
  (4,'2014-09-04',2,2);

INSERT INTO feedback VALUES
  (1,4,3,4,5,'Not bed',2,2),
  (2,5,4,5,4,'NULL',3,1);

INSERT INTO historyrecords VALUES
(1,'2014-09-04',1,2),
(2,'2014-09-04',2,1);

INSERT INTO historyrequest VALUES
  (1,'2014-09-08','2014-09-11',6,14,'5',1,0,200.00,550.00,'lviv',1,1,'2014-09-04'),
  (2,'2014-09-09','2014-09-14',7,16,'4,5',1,0,150.00,400.00,'lviv',2,1,'2014-09-04');