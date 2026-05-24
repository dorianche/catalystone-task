INSERT INTO PRODUCT (id, name, price, category) VALUES
(1, 'Laptop', 10.99, 'ELECTRONICS'),
(2, 'Mouse', 20.49, 'ELECTRONICS'),
(3, 'Keyboard', 15.75, 'ELECTRONICS'),
(8, 'Apple', 150, 'ELECTRONICS'),
(4, 'Yoghurt', 22, 'FOOD'),
(5, 'Bread', 29.78, 'FOOD'),
(6, 'Milk', 78.00, 'FOOD'),
(7, 'Apple', 10.89, 'FOOD');


INSERT INTO PURCHASE_ORDER_PRODUCT (id, purchase_order_id, product_id) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 2, 3);
