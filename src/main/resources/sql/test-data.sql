
insert into products (name, producer, productCode, producerProductCode)
values ('Bamper', 'DEPO', '000001', 'DEPO000001');
insert into products (name, producer, productCode, producerProductCode)
values ('Bamper', 'DEPO', '000002', 'DEPO000002');
insert into products (name, producer, productCode, producerProductCode)
values ('Bamper', 'DEPO', '000003', 'DEPO000003');
insert into products (name, producer, productCode, producerProductCode)
values ('Spoiler', 'DEPO', '000004', 'DEPO000004');
insert into products (name, producer, productCode, producerProductCode)
values ('Spoiler', 'FK-AUTOMOTIVE', '000005', 'F0000098');
insert into products (name, producer, productCode, producerProductCode)
values ('Spoiler', 'FK-AUTOMOTIVE', '000006', 'F0000034');
insert into products (name, producer, productCode, producerProductCode)
values ('Bamper', 'R-STYLE', '000007', 'GOLF00000234234');
insert into products (name, producer, productCode, producerProductCode)
values ('Bamper', 'R-STYLE', '000008', 'GOLF00000436512');


insert into cars (name, model, yearfromto) values ('Audi', 'A6', '2005-2009');
insert into cars (name, model, yearfromto) values ('Audi', 'A7', '2005-2009');
insert into cars (name, model, yearfromto) values ('BMW', '3', '2007-2010');
insert into cars (name, model, yearfromto) values ('BMW', '3', '2010-2015');
insert into cars (name, model, yearfromto) values ('BMW', '5', '2007-2010');
insert into cars (name, model, yearfromto) values ('BMW', '5', '2010-2015');
insert into cars (name, model, yearfromto) values ('VW', 'Golf GTI', '2009-2011');
insert into cars (name, model, yearfromto) values ('VW', 'Passat B6', '2005-2010');

insert into catalogs (name) values ('Bamper');
insert into catalogs (name) values ('Bamper Tuning');
insert into catalogs (name) values ('Bamper Standart');
insert into catalogs (name) values ('Spoiler');
insert into catalogs (name) values ('Spoiler Tuning');
insert into catalogs (name) values ('Spoiler Standart');

insert into product_cars (product_id, car_id) values (1, 1);
insert into product_cars (product_id, car_id) values (2, 1);
insert into product_cars (product_id, car_id) values (2, 2);
insert into product_cars (product_id, car_id) values (3, 3);
insert into product_cars (product_id, car_id) values (4, 3);
insert into product_cars (product_id, car_id) values (5, 4);
insert into product_cars (product_id, car_id) values (5, 6);
insert into product_cars (product_id, car_id) values (6, 3);
insert into product_cars (product_id, car_id) values (6, 5);
insert into product_cars (product_id, car_id) values (7, 7);
insert into product_cars (product_id, car_id) values (8, 8);

insert into product_catalogs (product_id, catalog_id) values (1, 1);
insert into product_catalogs (product_id, catalog_id) values (1, 2);
insert into product_catalogs (product_id, catalog_id) values (2, 1);
insert into product_catalogs (product_id, catalog_id) values (2, 3);
insert into product_catalogs (product_id, catalog_id) values (3, 1);
insert into product_catalogs (product_id, catalog_id) values (3, 2);
insert into product_catalogs (product_id, catalog_id) values (4, 4);
insert into product_catalogs (product_id, catalog_id) values (4, 5);
insert into product_catalogs (product_id, catalog_id) values (5, 4);
insert into product_catalogs (product_id, catalog_id) values (5, 5);
insert into product_catalogs (product_id, catalog_id) values (6, 4);
insert into product_catalogs (product_id, catalog_id) values (6, 5);
insert into product_catalogs (product_id, catalog_id) values (7, 1);
insert into product_catalogs (product_id, catalog_id) values (7, 2);
insert into product_catalogs (product_id, catalog_id) values (8, 1);
insert into product_catalogs (product_id, catalog_id) values (8, 2);

insert into users (username, password)
values ('admin', '$2a$11$47GlWUL8gt/CR4n.Br59M.vZID7RxezNRIq2MHBwqezS/Oeg0oZiG'); // pass: admin
insert into users values (2, 'testuser', '$2a$11$Uy6V11vQBWITENl8w4VRWO0tuCv6enFTjdpkDkXCc9E7HtLbcSAXm'); // pass: testuser

insert into roles values (1, 'ROLE_USER');
insert into roles values (2, 'ROLE_ADMIN');

insert into user_roles values (1, 2);
insert into user_roles values (2, 1);