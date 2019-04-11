/************ Add: Sequences ***************/

CREATE SEQUENCE public.seq_id INCREMENT BY 1;


/******************** Add Table: public.account ************************/

/* Build Table Structure */
CREATE TABLE public.account
(
	id BIGINT DEFAULT nextval('seq_id'::regclass) PRIMARY KEY,
	user_id BIGINT NOT NULL,
	currency_code VARCHAR(3) NOT NULL,
	balance NUMERIC(10, 2) NOT NULL,
	version BIGINT
) WITHOUT OIDS;