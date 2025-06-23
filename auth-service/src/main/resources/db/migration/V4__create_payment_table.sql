
CREATE TABLE payment_entity (
    id UUID PRIMARY KEY,
     purchase_order_id UUID NOT NULL,
     was_successful BOOLEAN NOT NULL,
     created_at DATE NOT NULL,
     updated_at DATE NOT NULL,
     FOREIGN KEY (purchase_order_id) REFERENCES purchase_order(id)
);