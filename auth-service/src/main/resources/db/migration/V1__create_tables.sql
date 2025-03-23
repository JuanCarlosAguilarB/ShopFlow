CREATE TABLE IF NOT EXISTS user_entity(
      id UUID PRIMARY KEY,
      username VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS product_entity{
      id UUID PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      description VARCHAR(255) NOT NULL,
      price DOUBLE PRESICION,
      quiantity INTEGER,
      created_at DATE
}

CREATE TABLE IF NOT EXISTS historical_product_price{
      price DOUBLE PRESICION,
      product_id UUID,
      created_at DATE,
      PRIMARY KEY(price, product_id, created_at)
}