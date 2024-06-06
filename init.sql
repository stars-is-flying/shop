CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE addr (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price INT NOT NULL,
    url VARCHAR(255)
);

CREATE TABLE message (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    content TEXT
);

CREATE TABLE `order` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    sum INT NOT NULL
);

CREATE TABLE box (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    price INT NOT NULL
);

-- 仓库
CREATE TABLE warehouse( 
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    url VARCHAR(255),
    price INT NOT NULL,
    count INT NOT NULL
);

INSERT INTO warehouse(name, url, price, count) VALUES
('chips', '1.jpg', '4', 4000),
('potato', '2.jpg', '11', 200),
('passion fruit', '3.jpg', '22', 400),
('spicy', '4.jpg', '2', 5000);

INSERT INTO admin (username, password) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3');
INSERT INTO addr(username, address) VALUES('kali', "东华大学27号楼5009");
INSERT INTO user (username, password, addr) VALUES ('kali', 'd6ca3fd0c3a3b462ff2b83436dda495e');

INSERT INTO product (name, description, price, url) VALUES
('Product 1',  'Description for Product 1', 19, 'img/1.jpg'),
('Product 2',  'Description for Product 2', 29, 'img/2.jpg'),
('Product 3',  'Description for Product 3', 39, 'img/3.jpg'),
('Product 4',  'Description for Product 4', 49, 'img/4.jpg'),
('Product 5',  'Description for Product 5', 59, 'img/5.jpg'),
('Product 6',  'Description for Product 6', 69, 'img/6.jpg'),
('Product 7',  'Description for Product 7', 79, 'img/7.jpg'),
('Product 8',  'Description for Product 8', 89, 'img/8.jpg'),
('Product 9',  'Description for Product 9', 99, 'img/9.jpg'),
('Product 10', 'Description for Product 10', 109, 'img/10.jpg');
