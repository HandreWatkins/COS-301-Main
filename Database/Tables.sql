-- Table: bookmark

-- DROP TABLE bookmark;

CREATE TABLE bookmark
(
  bookmark_id integer NOT NULL DEFAULT nextval('bookmark_id_seq'::regclass),
  date_time timestamp without time zone DEFAULT now(),
  user_requesting character varying(255) NOT NULL,
  discription character varying(255) NOT NULL,
  CONSTRAINT bookmark_id_pkey PRIMARY KEY (bookmark_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE bookmark
  OWNER TO postgres;


-- Table: distressedresources

-- DROP TABLE distressedresources;

CREATE TABLE distressedresources
(
  distressedresources_id integer NOT NULL DEFAULT nextval('distressedresources_id_seq'::regclass),
  last_update timestamp without time zone DEFAULT now(),
  time_expected integer NOT NULL,
  uri character varying(255),
  responsetime double precision,
  ip character varying(255),
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
  ip character varying(255) NOT NULL,
  uri character varying(255) NOT NULL,
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

CREATE TABLE mainactivity
(
  mainactivity_id integer NOT NULL DEFAULT nextval('mainactivity_id_seq'::regclass),
  create_date timestamp without time zone DEFAULT now(),
  ip character varying(255) NOT NULL,
  uri character varying(255) NOT NULL,
  "responseTime" double precision NOT NULL,
  CONSTRAINT mainactivity_id_pkey PRIMARY KEY (mainactivity_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE mainactivity
  OWNER TO postgres;

-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
  username character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  CONSTRAINT username_pkey PRIMARY KEY (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;
