CREATE TABLE orders_products (
  order_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL,
   CONSTRAINT pk_order_product PRIMARY KEY (order_id, product_id)
);

ALTER TABLE orders_products ADD CONSTRAINT fk_ordpro_on_order FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE orders_products ADD CONSTRAINT fk_ordpro_on_product FOREIGN KEY (product_id) REFERENCES products (id);