INSERT INTO brands (id,name) VALUES (1,'ZARA');
INSERT INTO products (id, name, brand_id) VALUES (35455, 'XYZ', 1);
INSERT INTO
    items(id, start_date, end_date, priority, price, currency, product_id, brand_id)
VALUES
(1,'2020-06-14T00.00.00','2020-12-31T23.59.59',0,35.50,'EUR',35455,1),
(2,'2020-06-14T15.00.00','2020-06-14T18.30.00',1,25.45,'EUR',35455,1),
(3,'2020-06-15T00.00.00','2020-06-15T11.00.00',1,30.50,'EUR',35455,1),
(4,'2020-06-15T16.00.00','2020-12-31T23.59.59',1,38.95,'EUR',35455,1);