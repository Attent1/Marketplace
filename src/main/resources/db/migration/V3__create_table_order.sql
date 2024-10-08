CREATE TABLE ORDERS (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    data TIMESTAMP NOT NULL,
    user_id UUID REFERENCES users (id) ON DELETE CASCADE,
    total DECIMAL(10, 2)
);
