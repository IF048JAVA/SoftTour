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
  (4,'Turkey'),
  (5, 'Cyprus');

INSERT INTO region VALUES
  (1,'Antalya',4),
  (2,'Sharm El Sheikh',1),
  (3,'Athens',3),
  (4, 'Ayia Napa', 5);

INSERT INTO hotel VALUES
  (1,'Antalya',5, 1, 3.2, 4.6, 2.3, 5.0, 4.2, 'http://www.danytur.com/images/gallery/hotelAntalya/rixos.jpg'),
  (2,'Viking Club',4, 2, 3.8, 4.0, 4.7, 3.6, 4.2, 'http://travelzest.ru/wp-content/gallery/viking-club-4/viking-club-4-4.jpg'),
  (3,'Amarilia',5, 3, 3.2, 4.6, 5.0, 4.0, 4.7, 'http://www.erasmus.gr/ConImgs/HOTELS/Amarilia_hotel.jpg'),
  (4, 'Egypt soul', 4, 2, 4.1, 4.0, 3.9, 3.8, 4.2, 'http://media-cdn.tripadvisor.com/media/photo-s/02/83/a6/00/mon-port-hotel-spa.jpg'),
  (5, 'Drunk Camel', 4, 2, 3.3, 4.1, 4.5, 4.3, 4.2, 'http://mashcurry.com/wp-content/uploads/2013/05/pestana-casino-park-hotel.jpg'),
  (6, 'Piramid', 3, 2, 4.4, 3.9, 4.2, 4.7, 4.8, 'http://ittour.com.ua/images/itt_hotel_image/1/3/9/3/4/3/file_name/imagehandler.jpg'),
  (7, 'Saritas Hotel', 4, 1, 3.2, 4.6, 4.2, 4.7, 4.2, 'http://ittour.com.ua/images/itt_hotel_image/1/6/2/8/4/7/file_name/11.jpg'),
  (8, 'Balaska', 3, 3, 4.2, 3.8, 4.0, 4.7, 4.7, 'http://ittour.com.ua/images/itt_hotel_image/2/0/2/5/9/8/file_name/greciya_afiny_balasca_3.jpg'),
  (9, 'Pavlinia Hotel Apts', 3, 4, 3.2, 4.6, 2.3, 5.0, 4.2, 'http://ittour.com.ua/images/itt_hotel_image/2/0/5/3/5/9/file_name/kipr_ayya_napa_pavlinia_hotel_apts_3.jpg'),
  (10, 'Mastronapa Hotel Apartments', 3, 4, 4.6, 4.2, 4.2, 4.0, 3.9, 'http://ittour.com.ua/images/itt_hotel_image/2/0/5/3/4/3/file_name/kipr_ayya_napa_mastronapa_hotel_apartments_3.jpg'),
  (11, 'Sun Rise Hotel', 3, 2,  4.1, 4.0, 3.9, 4.2, 4.0, 'http://ittour.com.ua/images/itt_hotel_image/1/3/9/3/4/3/file_name/imagehandler.jpg'),
  (12, 'Green Beyza Hotel', 3, 1, 4.2, 4.0, 3.9, 4.6, 2.3, 'http://ittour.com.ua/images/itt_hotel_image/1/6/2/3/8/4/file_name/pict1_783.jpg'),
  (13, 'Atalla Hotel Lara', 3, 1, 4.2, 4.7, 4.2, 4.2, 3.8, 'http://ittour.com.ua/images/itt_hotel_image/2/1/2/8/5/2/file_name/turtsiya_atalla_hotel_lara_3.jpg'),
  (14, 'Harrington Park Resort', 5, 1, 4.8, 4.7, 4.9, 4.6, 5.0, 'http://ittour.com.ua/images/itt_hotel_image/1/4/3/3/1/3/file_name/pict1_686.jpg');


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