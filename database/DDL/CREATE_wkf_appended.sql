-- 
-- CREATE_wkf_appended.sql
-- 
-- DROP TABLE wkf_appended;

CREATE  TABLE wkf_appended
 ( 
  id BIGSERIAL
 ,company_code VARCHAR (10)  NOT NULL
 ,application_number BIGINT NOT NULL
 ,approval_number serial
 ,approval_company_code VARCHAR(10) NOT NULL
 ,approval_unit_code VARCHAR(10) NOT NULL
 ,approval_user_code VARCHAR(10) NOT NULL
 ,approval_path VARCHAR(300) NOT NULL
 ,approval_date TIMESTAMP(0) NOT NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_appended_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;


COMMENT ON TABLE wkf_appended IS 'appended object';
COMMENT ON COLUMN wkf_appended.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_appended.company_code IS 'company code';
COMMENT ON COLUMN wkf_appended.application_number IS 'application number';
COMMENT ON COLUMN wkf_appended.approval_number IS 'approval number';
COMMENT ON COLUMN wkf_appended.approval_company_code IS 'approval company code';
COMMENT ON COLUMN wkf_appended.approval_unit_code IS 'approval unit code';
COMMENT ON COLUMN wkf_appended.approval_user_code IS 'approval user code';
COMMENT ON COLUMN wkf_appended.approval_path IS 'approval path';
COMMENT ON COLUMN wkf_appended.approval_date IS 'approval date';
COMMENT ON COLUMN wkf_appended.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_appended.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_appended.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_appended.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_appended_unique_idx1 on wkf_appended(company_code,application_number,approval_number);

alter TABLE wkf_appended
 add CONSTRAINT wkf_appended_fkey FOREIGN KEY ( company_code,application_number )
     REFERENCES wkf_application_object ( company_code,application_number )  ON UPDATE CASCADE ON DELETE CASCADE
