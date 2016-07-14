--
-- CREATE_wkf_application_form.sql
--
-- DROP TABLE wkf_application_form;

CREATE  TABLE wkf_application_form
 (
  id BIGSERIAL
 ,company_code VARCHAR (10)  NOT NULL
 ,application_form_code VARCHAR (10)  NULL
 ,application_form_name VARCHAR (30)  NULL
 ,application_classification_code VARCHAR (3)  NOT NULL
 ,skip_apply_user boolean
 ,auto_approval_flag boolean
 ,pulling_flag  boolean
 ,route_flag  int
 ,sort_order INTEGER NOT NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_application_form_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;

COMMENT ON TABLE  wkf_application_form IS 'application form master';
COMMENT ON COLUMN wkf_application_form.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_application_form.company_code IS 'company code';
COMMENT ON COLUMN wkf_application_form.application_form_code IS 'application form code';
COMMENT ON COLUMN wkf_application_form.application_form_name IS 'application form name';
COMMENT ON COLUMN wkf_application_form.application_classification_code IS 'application classification code';
COMMENT ON COLUMN wkf_application_form.skip_apply_user IS 'skip apply user';
COMMENT ON COLUMN wkf_application_form.auto_approval_flag IS 'approval flag';
COMMENT ON COLUMN wkf_application_form.pulling_flag IS 'pulling flag';
COMMENT ON COLUMN wkf_application_form.route_flag IS 'individual route segment';
COMMENT ON COLUMN wkf_application_form.sort_order IS 'sort order';
COMMENT ON COLUMN wkf_application_form.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_application_form.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_application_form.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_application_form.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_application_form_unique_idx1 on wkf_application_form(company_code,application_form_code);
