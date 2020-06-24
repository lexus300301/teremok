/*Шифрование паролей для клиента*/

DROP FUNCTION IF EXISTS password_chesh_customer CASCADE;

CREATE OR REPLACE FUNCTION password_chesh_customer() RETURNS TRIGGER AS $$
DECLARE
    id int;
    password varchar(50);
BEGIN
    IF    	TG_OP = 'INSERT' THEN
        	password = NEW.customer_password;
    		id = NEW.customer_id;

        UPDATE customer SET customer_password = md5(password) WHERE customer_id=id;
        RETURN NEW;
  END IF;
END;
$$ LANGUAGE plpgsql;


DROP TRIGGER IF EXISTS customer_password_trigger ON customer CASCADE;

CREATE TRIGGER customer_password_trigger
    AFTER INSERT ON customer
    FOR EACH ROW
    EXECUTE  PROCEDURE password_chesh_customer();


/*Шифрование паролей для админа*/

DROP FUNCTION IF EXISTS password_chesh_admin CASCADE;

CREATE OR REPLACE FUNCTION password_chesh_admin() RETURNS TRIGGER AS $$
DECLARE
    id int;
    password varchar(50);
BEGIN
    IF    	TG_OP = 'INSERT' THEN
        	password = NEW.admin_password;
    		id = NEW.admin_id;

        UPDATE admins SET admin_password = md5(password) WHERE admin_id=id;
        RETURN NEW;
  END IF;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS admin_password_trigger ON admins CASCADE;
CREATE TRIGGER admin_password_trigger
    AFTER INSERT ON admins
    FOR EACH ROW
    EXECUTE  PROCEDURE password_chesh_admin();


/*Добавление в корзину*/

DROP PROCEDURE IF EXISTS insert_procedure CASCADE;
CREATE PROCEDURE insert_procedure(order_id int, product_id int, quantity int )
LANGUAGE SQL
AS $$
INSERT INTO buylist(buylist_order_id, buylist_product_id, buylist_quantity) VALUES (order_id, product_id, quantity);

$$;


/*Изменение количества*/

DROP FUNCTION IF EXISTS quantity_reducer CASCADE;

CREATE OR REPLACE FUNCTION quantity_reducer() RETURNS TRIGGER AS $$
DECLARE
    id int;
  	quantity int;
BEGIN
    IF    	TG_OP = 'INSERT' THEN

    		select buylist_product_id into id from buylist
			where buylist_order_id =  NEW.buylist_order_id;

			select buylist_quantity into quantity from buylist
			where buylist_order_id =  NEW.buylist_order_id;

        UPDATE product SET product_quantity = product_quantity - quantity WHERE product_id = id;
        RETURN NEW;
  END IF;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS quantity_reducer_trigger ON orders CASCADE;

CREATE TRIGGER quantity_reducer_trigger
    AFTER INSERT ON buylist
    FOR EACH ROW
    EXECUTE  PROCEDURE quantity_reducer();


/*Представление полной информации о заказе*/

CREATE OR REPLACE VIEW orders_information
			(Id, total_price, Customer_login, Customer_Full_Name, Worker, Product, Quantity, Provider ) AS

SELECT  orders.order_id, orders.order_totalprice,customer.customer_login,
		concat(customer.customer_name, '  ', customer.customer_surname),
		concat (worker.worker_name, '  ', worker.worker_surname),
		product.product_name, buylist.buylist_quantity, provider.provider_name

from orders
JOIN customer ON (orders.order_customer = customer.customer_id)
JOIN worker ON (orders.order_worker = worker.worker_id)

JOIN product ON (product.product_id IN (select buylist.buylist_product_id from buylist
									   where buylist.buylist_order_id = orders.order_id))

JOIN buylist ON (buylist.buylist_order_id = orders.order_id AND buylist.buylist_product_id = product.product_id)

JOIN provider ON (provider.provider_id = product.product_provider
				  AND product.product_id IN (select buylist.buylist_product_id from buylist
									  		 where buylist.buylist_order_id = orders.order_id))

ORDER BY orders.order_id ASC;


/*Добавление товара на склад*/

CREATE PROCEDURE product_add (id integer, quantity integer)
LANGUAGE SQL
AS $$

    UPDATE product SET product_quantity = product_quantity +  quantity + 50 WHERE product_id = id;

$$;










