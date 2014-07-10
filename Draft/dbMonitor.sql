DROP DATABASE [ IF EXISTS ] dbMonitor;

CREATE DATABASE dbMonitor
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
       

CREATE SEQUENCE request_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 599
  CACHE 1;
ALTER TABLE request_id_seq
  OWNER TO postgres;

DROP TABLE request_Table;

CREATE TABLE request_Table
(
  request_Date integer NOT NULL DEFAULT nextval('request_id_seq'::regclass),
  request_Starttime smallint NOT NULL,
  request_endtime character varying(45) NOT NULL,
  overhead character varying(45) NOT NULL,
  failure rate character varying(50),
  httpStatus character varying(50) NOT NULL,
  activebool boolean NOT NULL DEFAULT true,
  create_date date NOT NULL DEFAULT ('now'::text)::date,
  last_update timestamp without time zone DEFAULT now(),
  active integer,
  CONSTRAINT customer_pkey PRIMARY KEY (customer_id),
 -- CONSTRAINT customer_address_id_fkey FOREIGN KEY (address_id)
    --  REFERENCES address (address_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE request_Table
  OWNER TO postgres;

-- Index: idx_fk_address_id

-- DROP INDEX idx_fk_address_id;

--CREATE INDEX idx_fk_address_id
 -- ON customer
 -- USING btree
  --(address_id);

-- Index: idx_fk_store_id

-- DROP INDEX idx_fk_store_id;

--CREATE INDEX idx_fk_store_id
 -- ON customer
 -- USING btree
--  (store_id);

-- Index: idx_last_name

-- DROP INDEX idx_last_name;

--CREATE INDEX idx_last_name
 -- ON customer
 -- USING btree
 -- (last_name COLLATE pg_catalog."default");


-- Trigger: last_updated on customer

-- DROP TRIGGER last_updated ON customer;

CREATE TRIGGER last_updated
  BEFORE UPDATE
  ON request_Table
FOR EACH ROW
  EXECUTE PROCEDURE last_updated();

