DROP DATABASE IF EXISTS softtour;
CREATE DATABASE softtour default charset=utf8 COLLATE=utf8_general_ci;

USE softtour;
SET NAMES 'utf8';
SET NAMES utf8;
SET NAMES utf8 COLLATE utf8_general_ci ;

CREATE TABLE role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,

  PRIMARY KEY (id)

);

CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(30) NOT NULL UNIQUE,
  email varchar(30) NOT NULL UNIQUE,
  password varchar(64) NOT NULL,
  birthday date NOT NULL,
  sex enum('MALE','FEMALE') NOT NULL,
  phone varchar(20) DEFAULT NULL,
  role_id bigint(20) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,

  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES role (id)

);

CREATE TABLE country (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  itTourId bigint (20) NOT NULL,

  PRIMARY KEY (id)

);

CREATE TABLE region (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  itTourId bigint (20) not null,
  country_id bigint(20) NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (country_id) REFERENCES country (id)

);

CREATE TABLE hotel (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  stars int(1) NOT NULL,
  region_id bigint(20) NOT NULL,
  feedbacksNum int(13) NOT NULL,
  rating decimal(2,1),
  comfort decimal(2,1),
  cleanliness decimal(2,1),
  location decimal(2,1),
  valueForMoney decimal(2,1),
  imgUrl TEXT (500),
  itTourId bigint (20) NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (region_id) REFERENCES region (id)

);

CREATE TABLE tour (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  date date NOT NULL,
  days int(11) NOT NULL,
  departureTime time NOT NULL,
  departureCity varchar(45) NOT NULL,
  price decimal(7,2) NOT NULL,
  adultAmount int(3) NOT NULL,
  childrenAmount int(3) NOT NULL,
  roomType enum('STD_SV','STD','SUPERIOR','DELUXE_SUPERIOR','FAMILY','APART','UNKNOWN') NOT NULL,
  food enum('HB','BB','FB','AI','UAI','RO') NOT NULL,
  hotel_id bigint(20) NOT NULL,


  PRIMARY KEY (id),
  FOREIGN KEY (hotel_id) REFERENCES hotel (id)

);

CREATE TABLE favorite (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  date date NOT NULL,
  user_id bigint(20) NOT NULL,
  tour_id bigint(20) NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (tour_id) REFERENCES tour (id)

);

CREATE TABLE feedback (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  cleanliness decimal(2,1) NOT NULL,
  comfort decimal(2,1) NOT NULL,
  location decimal(2,1) NOT NULL,
  valueForMoney decimal(2,1) NOT NULL,
  comment text,
  hotel_id bigint(20) NOT NULL,
  user_id bigint(20) NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (hotel_id) REFERENCES hotel (id),
  FOREIGN KEY (user_id) REFERENCES user (id)

);

CREATE TABLE historyrecords (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  date date NOT NULL,
  user_id bigint(20) NOT NULL,
  tour_id bigint(20) NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (tour_id) REFERENCES tour (id)

);

CREATE TABLE historyrequest (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dateFrom date NOT NULL,
  dateTo date NOT NULL,
  daysFrom int(2) NOT NULL,
  daysTo int(2) NOT NULL,
  stars set('2','3','4','5') NOT NULL,
  adults int(1) NOT NULL DEFAULT '1',
  children int(1) NOT NULL DEFAULT '0',
  priceFrom decimal(7,2) NOT NULL DEFAULT '0.00',
  priceTo decimal(7,2) NOT NULL DEFAULT '99000.00',
  cityFrom varchar(45) NOT NULL,
  user_id bigint(20) NOT NULL,
  country_id bigint(20) NOT NULL,
  requestDate date NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (country_id) REFERENCES country (id)

);


CREATE TABLE LOGS (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	date datetime NOT NULL,
	level varchar(10) NOT NULL,
	logger varchar(150) NOT NULL,
	method varchar(50) NOT NULL,
	line int(3) NOT NULL,
	message text NOT NULL,
	
	PRIMARY KEY (id)
);