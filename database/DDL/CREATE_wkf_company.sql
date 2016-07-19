-- Table: wkf_company

-- DROP TABLE wkf_company;

CREATE TABLE wkf_company
(
  id bigserial NOT NULL, -- surrogate key
  company_code character varying(10) NOT NULL, -- company code
  company_name character varying(30), -- company name
  create_date_time timestamp(0) without time zone NOT NULL, -- create date
  create_user_id character varying(18) NOT NULL, -- owner of record
  update_date_time timestamp(0) without time zone DEFAULT now(), -- update date
  update_user_id character varying(18) NOT NULL, -- record of the update
  CONSTRAINT wkf_company_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE wkf_company
  OWNER TO postgres;
COMMENT ON TABLE wkf_company
  IS 'company master';
COMMENT ON COLUMN wkf_company.id IS 'surrogate key';
COMMENT ON COLUMN wkf_company.company_code IS 'company code';
COMMENT ON COLUMN wkf_company.company_name IS 'company name';
COMMENT ON COLUMN wkf_company.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_company.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_company.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_company.update_user_id IS 'record of the update';


-- Index: wkf_company_unique_idx1

-- DROP INDEX wkf_company_unique_idx1;

CREATE UNIQUE INDEX wkf_company_unique_idx1
  ON wkf_company
  USING btree
  (company_code COLLATE pg_catalog."default");


