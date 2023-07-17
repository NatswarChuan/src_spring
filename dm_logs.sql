CREATE TABLE demo.dm_logs (
	log_id uuid NOT NULL DEFAULT gen_random_uuid(),
	"type" varchar NOT NULL,
	api varchar NOT NULL,
	created_at timestamp NOT NULL DEFAULT now(),
	description text NOT NULL,
	CONSTRAINT dm_logs_pk PRIMARY KEY (log_id)
);