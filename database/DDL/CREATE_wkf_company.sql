--
-- CREATE_wkf_company.sql
--
-- DROP TABLE wkf_company;

CREATE  TABLE wkf_company
 (
  id serial
 ,company_code VARCHAR (10)  NOT NULL
 ,company_name VARCHAR (30)  NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL
 
 ,CONSTRAINT wkf_company_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;

COMMENT ON TABLE  wkf_company IS 'company master';
COMMENT ON COLUMN wkf_company.id IS 'surrogate key';
COMMENT ON COLUMN wkf_company.company_code IS 'company code';
COMMENT ON COLUMN wkf_company.company_name IS 'company name';
COMMENT ON COLUMN wkf_company.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_company.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_company.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_company.update_user_id IS 'record of the update';
