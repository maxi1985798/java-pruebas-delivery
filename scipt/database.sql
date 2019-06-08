-- Database: delivery

-- DROP DATABASE delivery;

CREATE DATABASE delivery
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Spanish_Argentina.1252'
       LC_CTYPE = 'Spanish_Argentina.1252'
       CONNECTION LIMIT = -1;



-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
  user_name character varying(48) NOT NULL,
  name character varying(48) NOT NULL,
  last_name character varying(48) NOT NULL,
  email character varying(48) NOT NULL,
  mobile character varying(48) NOT NULL,
  address character varying(512) NOT NULL,
  password character varying(128) NOT NULL,
  CONSTRAINT pk_email PRIMARY KEY (email)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.users
  OWNER TO postgres;


