CREATE TABLE public.usuarios
(
    id bigint NOT NULL DEFAULT nextval('usuarios_id_seq'::regclass),
    nome character varying(20) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(12) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT usuarios_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usuarios
    OWNER to postgres;
    

CREATE TABLE public.bandas
(
    id bigint NOT NULL DEFAULT nextval('bandas_id_seq'::regclass),
    nome character varying(20) COLLATE pg_catalog."default" NOT NULL,
    genero character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT bandas_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bandas
    OWNER to postgres;
    
    
CREATE TABLE public.avaliacao
(
    id bigint NOT NULL DEFAULT nextval('avaliacao_id_seq'::regclass),
    id_usuario bigint NOT NULL,
    id_banda bigint NOT NULL,
    nota double precision NOT NULL,
    CONSTRAINT avaliacao_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.avaliacao
    OWNER to postgres;