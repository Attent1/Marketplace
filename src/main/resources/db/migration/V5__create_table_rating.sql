CREATE TABLE RATING (
        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
        score INT NOT NULL,
        commentary VARCHAR(500),
        user_id UUID REFERENCES USERS (id) ON DELETE CASCADE,
        product_id UUID REFERENCES PRODUCT (id) ON DELETE CASCADE
);
