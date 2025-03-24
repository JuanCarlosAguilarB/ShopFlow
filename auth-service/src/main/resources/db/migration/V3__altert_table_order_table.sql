
ALTER TABLE "orders" RENAME TO purchase_order_entity;

ALTER TABLE purchase_order_entity DROP COLUMN IF EXISTS status;

ALTER TABLE purchase_order_entity ADD COLUMN IF NOT EXISTS order_number VARCHAR(255);
ALTER TABLE purchase_order_entity ADD COLUMN IF NOT EXISTS update_at DATE;
ALTER TABLE purchase_order_entity ADD COLUMN IF NOT EXISTS was_closed BOOLEAN;
ALTER TABLE purchase_order_entity ADD COLUMN IF NOT EXISTS was_cancelled BOOLEAN;




