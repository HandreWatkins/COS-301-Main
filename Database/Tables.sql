

-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
  username text NOT NULL,
  password text NOT NULL,
  user_id integer NOT NULL DEFAULT nextval('user_id_sq'::regclass),
  CONSTRAINT user_id_pk PRIMARY KEY (user_id),
  CONSTRAINT uni_username UNIQUE (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;


-- Table: bookmark

-- DROP TABLE bookmark;

CREATE TABLE bookmark
(
   bookmark_id integer NOT NULL DEFAULT nextval('bookmark_id_seq'::regclass),
  date_time timestamp without time zone DEFAULT now(),
  user_requesting text NOT NULL,
  discription text NOT NULL,
  CONSTRAINT bookmark_id_pkey PRIMARY KEY (bookmark_id),
  CONSTRAINT user_bookmark_fk FOREIGN KEY (user_requesting)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT discription_unique UNIQUE (discription)
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

CREATE TABLE ai
(
  ai_id integer NOT NULL DEFAULT nextval('ai_id_seq'::regclass),
  _date text NOT NULL,
  _time text NOT NULL,
  uri text NOT NULL,
  count integer NOT NULL,
  total integer NOT NULL,
  CONSTRAINT ai_id_pkey PRIMARY KEY (ai_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ai
  OWNER TO postgres;
