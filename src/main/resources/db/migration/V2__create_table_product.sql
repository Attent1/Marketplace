CREATE TABLE PRODUCT (
     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
     name VARCHAR(100) NOT NULL,
     price DECIMAL(10, 2) NOT NULL,
     description VARCHAR(255),
     stock INT NOT NULL
);
