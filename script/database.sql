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
  confirmed boolean NOT NULL DEFAULT false,
  CONSTRAINT pk_email PRIMARY KEY (email)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.users
  OWNER TO postgres;


-- Table: public.statistic

-- DROP TABLE public.statistic;

CREATE TABLE public.statistic
(
  email character varying(48) NOT NULL,
  login time without time zone NOT NULL,
  logout time without time zone,
  id character varying(256) NOT NULL,
  CONSTRAINT pk_id PRIMARY KEY (id),
  CONSTRAINT fk_email FOREIGN KEY (email)
      REFERENCES public.users (email) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.statistic
  OWNER TO postgres;





  INSERT INTO public.users(
            user_name, name, last_name, email, mobile, address, password)
    VALUES ('hsimpson', 'Homer', 'Simpson', 'hsimpson@gmail.com', '+123456789', 'Springfield', 'cXdlcnR5');
