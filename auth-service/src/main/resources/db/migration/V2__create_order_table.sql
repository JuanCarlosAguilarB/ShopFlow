CREATE TABLE if not exists "orders"(
      id uuid PRIMARY KEY,
      user_id uuid NOT NULL,
      created_at DATE,
      delivery_date DATE,
      total DOUBLE PRECISION,
      status Boolean
);