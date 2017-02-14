
insert into products (name, producer, productCode, producerProductCode)
values ('Bamper', 'DEPO', '000001', 'DEPO000001');
insert into products (name, producer, productCode, producerProductCode)
values ('Bamper', 'DEPO', '000002', 'DEPO000002');
insert into products (name, producer, productCode, producerProductCode)
values ('Bamper', 'DEPO', '000003', 'DEPO000003');
insert into products (name, producer, productCode, producerProductCode)
values ('Spoiler', 'DEPO', '000004', 'DEPO000004');


insert into cars (name, model, yearfromto) values ('Audi', 'A6', '2005-2009');
insert into cars (name, model, yearfromto) values ('Audi', 'A7', '2005-2009');
insert into cars (name, model, yearfromto) values ('BMW', '3', '2007-2010');

insert into catalogs (name) values ('Bamper');
insert into catalogs (name) values ('Tuning Bamper');
insert into catalogs (name) values ('Standart Bamper');
insert into catalogs (name) values ('Spoiler');
insert into catalogs (name) values ('Tuning Spoiler');
insert into catalogs (name) values ('Standart Spoiler');

insert into product_cars (product_id, car_id) values (1, 1);
insert into product_cars (product_id, car_id) values (2, 1);
insert into product_cars (product_id, car_id) values (2, 2);
insert into product_cars (product_id, car_id) values (3, 3);
insert into product_cars (product_id, car_id) values (4, 3);

insert into product_catalogs (product_id, catalog_id) values (1, 1);
insert into product_catalogs (product_id, catalog_id) values (1, 2);
insert into product_catalogs (product_id, catalog_id) values (2, 1);
insert into product_catalogs (product_id, catalog_id) values (2, 3);
insert into product_catalogs (product_id, catalog_id) values (3, 1);
insert into product_catalogs (product_id, catalog_id) values (3, 2);
insert into product_catalogs (product_id, catalog_id) values (4, 4);
insert into product_catalogs (product_id, catalog_id) values (4, 5);
