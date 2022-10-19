-- database creation
CREATE USER docker;
CREATE DATABASE docker;
GRANT ALL PRIVILEGES ON DATABASE docker TO docker;

-- public.address definition

CREATE TABLE public.address (
	id SERIAL NOT NULL,
	"number" varchar(10) NOT NULL,
	zip_code varchar(9) NOT NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id)
);

INSERT INTO public.address
(id, "number", zip_code)
VALUES(1, 12, '64080-960');
INSERT INTO public.address
(id, "number", zip_code)
VALUES(2, 434, '80420-050');


-- public.customer definition

CREATE TABLE public.customer (
	id SERIAL NOT NULL,
	age varchar(3) NOT NULL,
	last_update_info timestamp NOT NULL,
	"name" varchar(255) NOT NULL,
	registration_date date NOT NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);

INSERT INTO public.customer
(id, age, last_update_info, "name", registration_date)
VALUES(1, 50, '2022-10-17 18:36:30.593', 'José', '2022-10-17 00:00:00.000');
INSERT INTO public.customer
(id, age, last_update_info, "name", registration_date)
VALUES(2, 40, '2022-10-17 19:55:55.950', 'John Lennon', '2022-10-17 00:00:00.000');


-- public.customer_addresses definition

CREATE TABLE public.customer_addresses (
	address_id SERIAL NOT NULL,
	customer_id SERIAL NOT NULL
);

-- public.customer_addresses foreign keys

ALTER TABLE public.customer_addresses ADD CONSTRAINT fk3ff0ggc5vqxxt6nc9ytpp217j FOREIGN KEY (customer_id) REFERENCES public.address(id);
ALTER TABLE public.customer_addresses ADD CONSTRAINT fkt0ab2p1446xfdmyxjottqrjpt FOREIGN KEY (address_id) REFERENCES public.customer(id);

INSERT INTO public.customer_addresses
(address_id, customer_id)
VALUES(1, 1);
INSERT INTO public.customer_addresses
(address_id, customer_id)
VALUES(2, 2);
