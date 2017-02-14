CREATE TABLE IF NOT EXISTS products (
    id INT NOT NULL AUTO_INCREMENT
  , name VARCHAR(255) NOT NULL
  , producer VARCHAR(255) NOT NULL
  , productCode VARCHAR(255) NOT NULL
  , producerProductCode VARCHAR(255) NOT NULL
  , PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  cars (
    id INT NOT NULL AUTO_INCREMENT
  , name VARCHAR(60) NOT NULL
  , model VARCHAR(40) NOT NULL
  , yearfromto VARCHAR(20) NOT NULL
  , PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS catalogs (
    id INT NOT NULL AUTO_INCREMENT
  , name VARCHAR(60) NOT NULL
  , PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product_cars (
    product_id INT NOT NULL,
    car_id     INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (car_id) REFERENCES cars (id),
    UNIQUE (product_id, car_id)
);

CREATE TABLE IF NOT EXISTS product_catalogs (
      product_id INT NOT NULL,
      catalog_id INT NOT NULL,
      FOREIGN KEY (product_id) REFERENCES products (id),
      FOREIGN KEY (catalog_id) REFERENCES catalogs (id),
      UNIQUE (product_id, catalog_id)
);