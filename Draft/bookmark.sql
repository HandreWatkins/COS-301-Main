USE dbMonitor;
       
CREATE SEQUENCE bookmark_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 599
  CACHE 1;
ALTER TABLE bookmark_id_seq
  OWNER TO postgres;

DROP TABLE Bookmark;

CREATE TABLE Bookmark
(
  bookmark_id integer NOT NULL DEFAULT nextval('bookmark_id_seq'::regclass),
  date_Time timestamp without time zone DEFAULT now(),
  user_requesting character varying(255) NOT NULL,
  discription character varying(255) NOT NULL,
  --exception_error character varying(255) NOT NULL,
  --request_start_time character varying(255) NOT NULL,
  --request_end_time character varying(255) NOT NULL,
  --response_Benchmark_time integer,
  
   --character varying(45) NOT NULL,
  --failurerate character varying(50),
  --httpStatus character varying(50) NOT NULL,
  --activebool boolean NOT NULL DEFAULT true,
  
 -- last_update timestamp without time zone DEFAULT now(),
  
  CONSTRAINT bookmark_id_pkey PRIMARY KEY (bookmark_id),
 -- CONSTRAINT userlastUpdate_pkey PRIMARY KEY (last_Update),
 -- CONSTRAINT customer_address_id_fkey FOREIGN KEY (address_id)
    --  REFERENCES address (address_id) MATCH SIMPLE
     -- ON UPDATE CASCADE ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Bookmark
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

