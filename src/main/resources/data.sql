INSERT INTO categories (name) VALUES
                                  ('Electronics'),
                                  ('Clothing'),
                                  ('Books'),
                                  ('Home');



INSERT INTO products (name, image_url, price, categories_id) VALUES
-- Electronics (id = 1)
('iPhone 14', 'https://gadgetstore.kz/wa-data/public/shop/products/72/04/472/images/1755/1755.970.jpeg', 999.99, 1),
('Samsung Galaxy S23', 'https://cdn.new-brz.net/app/public/models/SM-S916BZKDSEK/large/w/231009140026582955.webp', 899.99, 1),
('MacBook Air M2', 'https://gadgetstore.kz/wa-data/public/shop/products/95/04/495/images/1813/1813.970.jpeg', 1199.99, 1),
('Sony Headphones', 'https://www.avmk.kz/image/cache/catalog/pu/930d2c9c86c28fb5-1280x1280.jpg', 199.99, 1),

-- Clothing (id = 2)
('T-Shirt', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlp-Ltk9qnyjnLsGhF5p4Tx5n8T83yCg9Zfg&s', 19.99, 2),
('Jeans', 'https://xcdn.next.co.uk/common/items/default/default/itemimages/3_4Ratio/product/lge/530205s6.jpg?im=Resize,width=750', 49.99, 2),
('Jacket', 'https://www.justbrand.co.za/cdn/shop/files/Alaska-Puffer-Jacket-Navy1.jpg?v=1717744975&width=1080', 89.99, 2),
('Sneakers', 'https://fausto.in/cdn/shop/files/FSTKI-698WHITE_MoodShot_1_400x.jpg?v=1716306331', 79.99, 2),

-- Books (id = 3)
('Clean Code', 'https://m.media-amazon.com/images/I/71nj3JM-igL._AC_UF1000,1000_QL80_.jpg', 29.99, 3),
('Java Basics', 'https://m.media-amazon.com/images/I/71KeV0CDG1L._UF1000,1000_QL80_.jpg', 24.99, 3),
('Spring in Action', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAY084qK1OzjNlnFj569cDIPrVRG6q2oEiTg&s', 39.99, 3),
('Algorithms', 'https://m.media-amazon.com/images/I/61-8ZU7X3UL._AC_UF1000,1000_QL80_.jpg', 34.99, 3),

-- Home (id = 4)
('Chair', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShXCU7gvMEYA-tOoY_nYflTVkMy3DIuUMvFg&s', 59.99, 4),
('Table', 'https://img.freepik.com/premium-vector/illustration-wooden-dining-table-isolated_756535-8226.jpg?semt=ais_incoming&w=740&q=80', 129.99, 4),
('Lamp', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0X8AFe1MDCHnooPcJpGxGZmXrSX5tx3tb6g&s', 25.99, 4),
('Sofa', 'https://www.duroflexworld.com/cdn/shop/files/1_6.jpg?v=1746564300', 499.99, 4);



INSERT INTO reviews (rating, review_owner, comment, products_id) VALUES
-- iPhone 14 (id = 1)
('FIVE_STAR', 'Alice', 'Amazing phone!', 1),
('FOUR_STAR', 'Bob', 'Very good but expensive', 1),

-- MacBook Air (id = 3)
('FIVE_STAR', 'Charlie', 'Perfect for development', 3),

-- T-Shirt (id = 5)
('FOUR_STAR', 'David', 'Comfortable and cheap', 5),

-- Sneakers (id = 8)
('FIVE_STAR', 'Emma', 'Very stylish!', 8),
('THREE_STAR', 'Frank', 'Okay quality', 8),

-- Clean Code (id = 9)
('FIVE_STAR', 'George', 'Must read for developers', 9),

-- Sofa (id = 16)
('FOUR_STAR', 'Helen', 'Comfortable but big', 16);