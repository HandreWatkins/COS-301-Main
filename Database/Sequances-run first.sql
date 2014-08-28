-- Sequence: bookmark_id_seq

-- DROP SEQUENCE bookmark_id_seq;

CREATE SEQUENCE bookmark_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE bookmark_id_seq
  OWNER TO postgres;


-- Sequence: distressedresources_id_seq

-- DROP SEQUENCE distressedresources_id_seq;

CREATE SEQUENCE distressedresources_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE distressedresources_id_seq
  OWNER TO postgres;

-- Sequence: mainactivity_id_seq

-- DROP SEQUENCE mainactivity_id_seq;

CREATE SEQUENCE mainactivity_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE mainactivity_id_seq
  OWNER TO postgres;


-- Sequence: rules_id_seq

-- DROP SEQUENCE rules_id_seq;

CREATE SEQUENCE rules_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE rules_id_seq
  OWNER TO postgres;
  
  -- Sequence: user_id_sq

-- DROP SEQUENCE user_id_sq;

CREATE SEQUENCE user_id_sq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE user_id_sq
  OWNER TO postgres;

