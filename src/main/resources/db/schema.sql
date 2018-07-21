CREATE TABLE offer (
  id INT(6) AUTO_INCREMENT,
  offer_number VARCHAR(10) NOT NULL,
  start_date DATE NOT NULL,
  duration INT(6) NOT NULL,
  frequency VARCHAR(10) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE insurance (
  id INT(6) AUTO_INCREMENT,
  code VARCHAR(10) NOT NULL,
  sum DECIMAL(10,2) NOT NULL,
  base_product VARCHAR(1) NOT NULL,
  offer_id INT(6),
  PRIMARY KEY(id),
  FOREIGN KEY (offer_id) references offer(id)
);

CREATE TABLE person (
  id INT(6) AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  middle_name VARCHAR(50),
  last_name VARCHAR(50) NOT NULL,
  birth_date DATE NOT NULL,
  birth_place VARCHAR(50),
  birth_country VARCHAR(50),
  phone_number VARCHAR(50) NOT NULL,
  pesel VARCHAR(11),
  sex VARCHAR(50),
  identity_document_number VARCHAR(50),
  email VARCHAR(50),
  offer_id INT(6),
  PRIMARY KEY(id),
  FOREIGN KEY (offer_id) references offer(id)
);

CREATE TABLE address (
  id INT(6) AUTO_INCREMENT,
  person_id INT(6),
  country VARCHAR(100),
  postal_code VARCHAR(20),
  post_office VARCHAR(100),
  city VARCHAR(100),
  street VARCHAR(100),
  building_number VARCHAR(10),
  flat_number VARCHAR(10),
  PRIMARY KEY(id),
  FOREIGN KEY (person_id) references person(id)
);