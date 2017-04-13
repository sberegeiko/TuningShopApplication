
-- Table: cars
CREATE TABLE IF NOT EXISTS cars (
  id         SERIAL       NOT NULL PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  model      VARCHAR(255) NOT NULL,
  yearFromTo VARCHAR(255) NOT NULL
);

-- Table: catalogs
CREATE TABLE IF NOT EXISTS catalogs (
  id   SERIAL       NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

-- Table: products
CREATE TABLE IF NOT EXISTS products (
  id                  SERIAL       NOT NULL PRIMARY KEY,
  name                VARCHAR(255) NOT NULL,
  producer            VARCHAR(255) NOT NULL,
  productCode         VARCHAR(255) NOT NULL,
  producerProductCode VARCHAR(255) NOT NULL
);

-- Table for mapping product and cars: product_cars
CREATE TABLE IF NOT EXISTS product_cars (
  product_id INTEGER NOT NULL,
  car_id     INTEGER NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id),
  FOREIGN KEY (car_id) REFERENCES cars (id),
  UNIQUE (product_id, car_id)
);

-- Table for mapping product and catalogs: product_catalogs
CREATE TABLE IF NOT EXISTS product_catalogs (
  product_id INTEGER NOT NULL,
  catalog_id INTEGER NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id),
  FOREIGN KEY (catalog_id) REFERENCES catalogs (id),
  UNIQUE (product_id, catalog_id)
);

-- Insert data
INSERT INTO cars (name, model, yearfromto) VALUES ('Audi', 'A6', '2005-2009');
INSERT INTO cars (name, model, yearfromto) VALUES ('Audi', 'A7', '2005-2009');
INSERT INTO cars (name, model, yearfromto) VALUES ('BMW', '3', '2007-2010');
INSERT INTO cars (name, model, yearfromto) VALUES ('BMW', '3', '2010-2015');
INSERT INTO cars (name, model, yearfromto) VALUES ('BMW', '5', '2007-2010');
INSERT INTO cars (name, model, yearfromto) VALUES ('BMW', '5', '2010-2015');
INSERT INTO cars (name, model, yearfromto) VALUES ('VW', 'Golf GTI', '2009-2011');
INSERT INTO cars (name, model, yearfromto) VALUES ('VW', 'Passat B6', '2005-2010');

INSERT INTO products (name, producer, productCode, producerProductCode)
VALUES ('Bamper', 'DEPO', '000001', 'DEPO000001');
INSERT INTO products (name, producer, productCode, producerProductCode)
VALUES ('Bamper', 'DEPO', '000002', 'DEPO000002');
INSERT INTO products (name, producer, productCode, producerProductCode)
VALUES ('Bamper', 'DEPO', '000003', 'DEPO000003');
INSERT INTO products (name, producer, productCode, producerProductCode)
VALUES ('Spoiler', 'DEPO', '000004', 'DEPO000004');
INSERT INTO products (name, producer, productCode, producerProductCode)
VALUES ('Spoiler', 'FK-AUTOMOTIVE', '000005', 'F0000098');
INSERT INTO products (name, producer, productCode, producerProductCode)
VALUES ('Spoiler', 'FK-AUTOMOTIVE', '000006', 'F0000034');
INSERT INTO products (name, producer, productCode, producerProductCode)
VALUES ('Bamper', 'R-STYLE', '000007', 'GOLF00000234234');
INSERT INTO products (name, producer, productCode, producerProductCode)
VALUES ('Bamper', 'R-STYLE', '000008', 'GOLF00000436512');

INSERT INTO catalogs (name) VALUES ('Bamper');
INSERT INTO catalogs (name) VALUES ('Bamper Tuning');
INSERT INTO catalogs (name) VALUES ('Bamper Standart');
INSERT INTO catalogs (name) VALUES ('Spoiler');
INSERT INTO catalogs (name) VALUES ('Spoiler Tuning');
INSERT INTO catalogs (name) VALUES ('Spoiler Standart');

INSERT INTO product_cars (product_id, car_id) VALUES (1, 1);
INSERT INTO product_cars (product_id, car_id) VALUES (2, 1);
INSERT INTO product_cars (product_id, car_id) VALUES (2, 2);
INSERT INTO product_cars (product_id, car_id) VALUES (3, 3);
INSERT INTO product_cars (product_id, car_id) VALUES (4, 3);
INSERT INTO product_cars (product_id, car_id) VALUES (5, 4);
INSERT INTO product_cars (product_id, car_id) VALUES (5, 6);
INSERT INTO product_cars (product_id, car_id) VALUES (6, 3);
INSERT INTO product_cars (product_id, car_id) VALUES (6, 5);
INSERT INTO product_cars (product_id, car_id) VALUES (7, 7);
INSERT INTO product_cars (product_id, car_id) VALUES (8, 8);

INSERT INTO product_catalogs (product_id, catalog_id) VALUES (1, 1);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (1, 2);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (2, 1);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (2, 3);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (3, 1);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (3, 2);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (4, 4);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (4, 5);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (5, 4);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (5, 5);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (6, 4);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (6, 5);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (7, 1);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (7, 2);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (8, 1);
INSERT INTO product_catalogs (product_id, catalog_id) VALUES (8, 2);

-- Table: users
CREATE TABLE IF NOT EXISTS users (
  id       SERIAL       NOT NULL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

-- Table: roles
CREATE TABLE IF NOT EXISTS roles (
  id   SERIAL       NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

-- Table for mapping user and roles: user_roles
CREATE TABLE IF NOT EXISTS user_roles (
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),
  UNIQUE (user_id, role_id)
);

-- Insert data
INSERT INTO users (username, password)
VALUES ('admin', '$2a$11$47GlWUL8gt/CR4n.Br59M.vZID7RxezNRIq2MHBwqezS/Oeg0oZiG'); /*pass: admin*/

INSERT INTO users (username, password)
VALUES ('testuser', '$2a$11$Uy6V11vQBWITENl8w4VRWO0tuCv6enFTjdpkDkXCc9E7HtLbcSAXm'); /*pass:testuser*/

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 2);
INSERT INTO user_roles VALUES (2, 1);
