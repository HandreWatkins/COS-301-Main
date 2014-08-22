-- Table: bookmark

-- DROP TABLE bookmark;

CREATE TABLE bookmark
(
  bookmark_id integer NOT NULL DEFAULT nextval('bookmark_id_seq'::regclass),
  date_time timestamp without time zone DEFAULT now(),
  user_requesting text NOT NULL,
  discription text NOT NULL,
  CONSTRAINT bookmark_id_pkey PRIMARY KEY (bookmark_id),
  CONSTRAINT user_name_bookmark_fk FOREIGN KEY (user_requesting)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE bookmark
  OWNER TO postgres;

CREATE INDEX fki_user_name_bookmark_fk
  ON bookmark
  USING btree
  (user_requesting COLLATE pg_catalog."default");

-- Table: distressedresources

-- DROP TABLE distressedresources;

CREATE TABLE distressedresources
(
  distressedresources_id integer NOT NULL DEFAULT nextval('distressedresources_id_seq'::regclass),
  last_update timestamp without time zone DEFAULT now(),
  time_expected integer NOT NULL,
  uri text,
  responsetime double precision,
  ip text,
  CONSTRAINT distressedresources_id_pkey PRIMARY KEY (distressedresources_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE distressedresources
  OWNER TO postgres;


-- Table: mainactivity

-- DROP TABLE mainactivity;

CREATE TABLE mainactivity
(
  mainactivity_id integer NOT NULL DEFAULT nextval('mainactivity_id_seq'::regclass),
  create_date timestamp without time zone DEFAULT now(),
  ip text NOT NULL,
  uri text NOT NULL,
  "responseTime" double precision NOT NULL,
  CONSTRAINT mainactivity_id_pkey PRIMARY KEY (mainactivity_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE mainactivity
  OWNER TO postgres;
  
  -- Table: mainactivity

-- DROP TABLE mainactivity;

CREATE TABLE rules
(
  rules_id integer NOT NULL DEFAULT nextval('rules_id_seq'::regclass),
  date_time timestamp without time zone DEFAULT now(),
  user_requesting text NOT NULL,
  expected_time double precision NOT NULL,
  uri text,
  CONSTRAINT rules_id_pkey PRIMARY KEY (rules_id),
  CONSTRAINT user_name_fk FOREIGN KEY (user_requesting)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rules
  OWNER TO postgres;

-- Index: fki_user_name_fk

-- DROP INDEX fki_user_name_fk;

CREATE INDEX fki_user_name_fk
  ON rules
  USING btree
  (user_requesting COLLATE pg_catalog."default");
-- Table: users

-- DROP TABLE users;

CREATE TABLE rules
(
  rules_id integer NOT NULL DEFAULT nextval('rules_id_seq'::regclass),
  date_time timestamp without time zone DEFAULT now(),
  user_requesting text NOT NULL,
  expected_time double precision NOT NULL,
  uri text,
  CONSTRAINT rules_id_pkey PRIMARY KEY (rules_id),
  CONSTRAINT user_name_fk FOREIGN KEY (user_requesting)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rules
  OWNER TO postgres;

-- Index: fki_user_name_fk

-- DROP INDEX fki_user_name_fk;

CREATE INDEX fki_user_name_fk
  ON rules
  USING btree
  (user_requesting COLLATE pg_catalog."default");