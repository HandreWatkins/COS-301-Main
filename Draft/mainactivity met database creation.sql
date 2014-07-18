DROP DATABASE [ IF EXISTS ] dbMonitor;

CREATE DATABASE dbMonitor
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;

CREATE OR REPLACE FUNCTION last_updated()
  RETURNS trigger AS
$BODY$
BEGIN
    NEW.last_update = CURRENT_TIMESTAMP;
    RETURN NEW;
END $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION last_updated()
  OWNER TO postgres;
       

CREATE SEQUENCE request_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 599
  CACHE 1;
ALTER TABLE request_id_seq
  OWNER TO postgres;

DROP TABLE MainActivity;

CREATE TABLE MainActivity
(
  mainActivity_id integer NOT NULL DEFAULT nextval('request_id_seq'::regclass),
  create_date timestamp without time zone DEFAULT now(),
  ip character varying(255) NOT NULL,
  --project_create_date character varying(255) NOT NULL,
  url character varying(255) NOT NULL,
  responseStart timestamp without time zone NOT NULL,
  responseEnd timestamp without time zone,
  active boolean NOT NULL,
  
   --character varying(45) NOT NULL,
  --failurerate character varying(50),
  --httpStatus character varying(50) NOT NULL,
  --activebool boolean NOT NULL DEFAULT true,
  
 -- last_update timestamp without time zone DEFAULT now(),
  
  CONSTRAINT mainActivity_id_pkey PRIMARY KEY (mainActivity_id),
 -- CONSTRAINT userlastUpdate_pkey PRIMARY KEY (last_Update),
 -- CONSTRAINT customer_address_id_fkey FOREIGN KEY (address_id)
    --  REFERENCES address (address_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE MainActivity
  OWNER TO postgres;

ALTER SEQUENCE request_id_seq RESTART WITH 1;
UPDATE t SET idcolumn=nextval('request_id_seq');

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

