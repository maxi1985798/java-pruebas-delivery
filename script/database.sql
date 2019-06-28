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



CREATE TABLE public.restaurants
(
    id smallint NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(200) COLLATE pg_catalog."default" NOT NULL,
    photo_link character varying(200) COLLATE pg_catalog."default" NOT NULL,
    foods character varying(200) COLLATE pg_catalog."default" NOT NULL,
    description character varying(200) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT restaurants_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

INSERT INTO public.restaurants(
	id, name, phone, photo_link, foods, description)
	VALUES (1, 'guerrin', '011 4371-8141','http://salpimenta.com.ar/wp-content/uploads/2015/08/Fachada-Guerrin.jpg', 'pizzas,empanadas', 'La mejor pizza de arg');
	
INSERT INTO public.restaurants(
	id, name, phone, photo_link, foods, description)
	VALUES (2, 'Don Julio', '011 4832-6058', 'https://img.guiaoleo.com.ar/unsafe/273x162/filters:fill(green)/Don-Julio-802-d1fc1f34-6194-476c-85ae-5affad239fd3', 'parrilla', 'talvez la mejor carne');
	
INSERT INTO public.restaurants(
	id, name, phone, photo_link, foods, description)
	VALUES (3, 'Chorimovil', 'no phone', 'https://www.clubtwister.com.ar/foro/uploader2/viewimage.php?image=4828/1326584865/101_3726.jpg', 'chorizo', 'el mejor chori');

  INSERT INTO public.users(
            user_name, name, last_name, email, mobile, address, password, confirmed)
    VALUES ('hsimpson', 'Homer', 'Simpson', 'hsimpson@gmail.com', '+123456789', 'Springfield', 'cXdlcnR5', true);
