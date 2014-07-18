USE dbMonitor;

DROP TABLE Users;

CREATE TABLE Users
(
  --username timestamp without time zone DEFAULT now(),
  username character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  
  --exception_error character varying(255) NOT NULL,
  --request_start_time character varying(255) NOT NULL,
  --request_end_time character varying(255) NOT NULL,
  --response_Benchmark_time integer,
  
   --character varying(45) NOT NULL,
  --failurerate character varying(50),
  --httpStatus character varying(50) NOT NULL,
  --activebool boolean NOT NULL DEFAULT true,
  
 -- last_update timestamp without time zone DEFAULT now(),
  
 -- CONSTRAINT userDefinedRulesId_pkey PRIMARY KEY (user_defined_id),
 CONSTRAINT username_pkey PRIMARY KEY (username),
 -- CONSTRAINT customer_address_id_fkey FOREIGN KEY (address_id)
    --  REFERENCES address (address_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Users
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
