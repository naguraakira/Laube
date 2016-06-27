--
-- CREATE_wkf_application_object.sql
--
-- DROP TABLE wkf_application_object;

CREATE  TABLE wkf_application_object
 (
  id serial
 ,company_code VARCHAR (10)  NOT NULL
 ,application_number BIGINT NOT NULL
 ,reApplication_number BIGINT NOT NULL
 ,application_form_code VARCHAR (10)  NULL
 ,apply_company_code VARCHAR(10) NOT NULL
 ,apply_unit_code VARCHAR(10) NOT NULL
 ,apply_user_code VARCHAR(10) NOT NULL
 ,deputy_apply_company_code  VARCHAR(10)
 ,deputy_apply_unit_code  VARCHAR(10)
 ,deputy_apply_user_code  VARCHAR(10)
 ,apply_date DATE NULL
 ,application_status INT NOT NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_application_object_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;


COMMENT ON TABLE wkf_application_object IS 'application object';
COMMENT ON COLUMN wkf_application_object.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_application_object.company_code IS 'company code';
COMMENT ON COLUMN wkf_application_object.application_number IS 'application number';
COMMENT ON COLUMN wkf_application_object.reApplication_number IS 'reApplication number';
COMMENT ON COLUMN wkf_application_object.application_form_code IS 'application form code';
COMMENT ON COLUMN wkf_application_object.apply_company_code IS 'apply company code';
COMMENT ON COLUMN wkf_application_object.apply_unit_code IS 'apply unit code';
COMMENT ON COLUMN wkf_application_object.apply_user_code IS 'apply user code';
COMMENT ON COLUMN wkf_application_object.deputy_apply_company_code IS 'deputy apply company code';
COMMENT ON COLUMN wkf_application_object.deputy_apply_unit_code IS 'deputy apply unit code';
COMMENT ON COLUMN wkf_application_object.deputy_apply_user_code IS 'deputy app]y user code';
COMMENT ON COLUMN wkf_application_object.apply_date IS 'apply date';
COMMENT ON COLUMN wkf_application_object.application_status IS 'application status';
COMMENT ON COLUMN wkf_application_object.create_date_time IS 'cretate date';
COMMENT ON COLUMN wkf_application_object.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_application_object.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_application_object.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_application_object_unique_idx1 on wkf_application_object(company_code,application_number);
