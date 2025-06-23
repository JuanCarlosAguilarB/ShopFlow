CREATE TABLE purchase_order(
   id UUID PRIMARY KEY,
   user_id UUID  REFERENCES  user_entity(id),
   created_at DATE,
   delivery_date DATE,
   order_number VARCHAR(255),
   total DOUBLE PRECISION,
   updated_at DATE,
   was_closed BOOLEAN,
   was_cancelled BOOLEAN
);