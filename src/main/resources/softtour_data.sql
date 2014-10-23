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

INSERT INTO country VALUES
  (1, 'Египет'),
  (2, 'Болгарія'),
  (3, 'Греція'),
  (4, 'Турція'),
  (5, 'Кіпр');

INSERT INTO region VALUES
  (1, 'Antalya', 4),
  (2, 'Sharm El Sheikh', 1),
  (3, 'Athens', 3),
  (4, 'Ayia Napa', 5);

INSERT INTO hotel VALUES
  (1, 'Antalya', 5, 1, 10, 3.2, 4.6, 2.3, 5.0, 4.2, 'http://www.danytur.com/images/gallery/hotelAntalya/rixos.jpg'),
  (2, 'Viking Club', 4, 2, 8, 3.8, 4.0, 4.7, 3.6, 4.2, 'http://travelzest.ru/wp-content/gallery/viking-club-4/viking-club-4-4.jpg'),
  (3, 'Amarilia', 5, 3, 12, 3.2, 4.6, 5.0, 4.0, 4.7, 'http://www.erasmus.gr/ConImgs/HOTELS/Amarilia_hotel.jpg'),
  (4, 'Egypt soul', 4, 2, 32, 4.1, 4.0, 3.9, 3.8, 4.2, 'http://media-cdn.tripadvisor.com/media/photo-s/02/83/a6/00/mon-port-hotel-spa.jpg'),
  (5, 'Drunk Camel', 4, 2, 9, 3.3, 4.1, 4.5, 4.3, 4.2, 'http://mashcurry.com/wp-content/uploads/2013/05/pestana-casino-park-hotel.jpg'),
  (6, 'Piramid', 3, 2, 13, 4.4, 3.9, 4.2, 4.7, 4.8, 'http://ittour.com.ua/images/itt_hotel_image/1/3/9/3/4/3/file_name/imagehandler.jpg'),
  (7, 'Saritas Hotel', 4, 1, 20, 3.2, 4.6, 4.2, 4.7, 4.2, 'http://ittour.com.ua/images/itt_hotel_image/1/6/2/8/4/7/file_name/11.jpg'),
  (8, 'Balaska', 3, 3, 16, 4.2, 3.8, 4.0, 4.7, 4.7, 'http://ittour.com.ua/images/itt_hotel_image/2/0/2/5/9/8/file_name/greciya_afiny_balasca_3.jpg'),
  (9, 'Pavlinia Hotel Apts', 3, 4, 11, 3.2, 4.6, 2.3, 5.0, 4.2, 'http://ittour.com.ua/images/itt_hotel_image/2/0/5/3/5/9/file_name/kipr_ayya_napa_pavlinia_hotel_apts_3.jpg'),
  (10, 'Mastronapa Hotel Apartments', 3, 4, 12, 4.6, 4.2, 4.2, 4.0, 3.9, 'http://ittour.com.ua/images/itt_hotel_image/2/0/5/3/4/3/file_name/kipr_ayya_napa_mastronapa_hotel_apartments_3.jpg'),
  (11, 'Sun Rise Hotel', 3, 2, 8, 4.1, 4.0, 3.9, 4.2, 4.0, 'http://ittour.com.ua/images/itt_hotel_image/1/3/9/3/4/3/file_name/imagehandler.jpg'),
  (12, 'Green Beyza Hotel', 3, 1, 9, 4.2, 4.0, 3.9, 4.6, 2.3,
   'http://ittour.com.ua/images/itt_hotel_image/1/6/2/3/8/4/file_name/pict1_783.jpg'),
  (13, 'Atalla Hotel Lara', 3, 1, 7, 4.2, 4.7, 4.2, 4.2, 3.8,
   'http://ittour.com.ua/images/itt_hotel_image/2/1/2/8/5/2/file_name/turtsiya_atalla_hotel_lara_3.jpg'),
  (14, 'Harrington Park Resort', 5, 1, 15, 4.8, 4.7, 4.9, 4.6, 5.0,
   'http://ittour.com.ua/images/itt_hotel_image/1/4/3/3/1/3/file_name/pict1_686.jpg');

INSERT INTO tour VALUES
  (1, '2014-11-01', 6, '11:30:00', 'Київ', 242.00, 3, 2,'APART', 'HB', 1),
  (2, '2014-11-01', 10, '16:00:00', 'Львів', 350.00, 4, 2,'UNKNOWN', 'BB', 2),
  (3, '2014-11-03', 6, '14:30:00', 'Київ', 750.00, 3, 0,'STD', 'HB', 4),
  (4, '2014-11-02', 6, '20:00:00', 'Львів', 450.00, 4, 2,'DELUXE_SUPERIOR', 'FB', 2),
  (5, '2014-11-04', 6, '17:30:00', 'Київ', 600.00, 1, 0,'STD', 'AI', 5),
  (6, '2014-11-02', 6, '15:00:00', 'Львів', 250.00, 2, 2,'SUPERIOR','UAI', 3);

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