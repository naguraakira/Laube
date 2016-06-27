-- 
-- CREATE_wkf_application_classification.sql
-- 
--         Automatic creation by LettyTools. 2015/10/16 12:30:50
-- 
-- DROP TABLE wkf_application_classification;

CREATE  TABLE wkf_application_classification
 ( 
  id BIGSERIAL
 ,company_code VARCHAR (10)  NOT NULL
 ,application_classification_code VARCHAR (10)  NOT NULL 
 ,application_classification_name VARCHAR (30)  NULL 
 ,sort_order INTEGER NOT NULL 
 ,management_unit_code VARCHAR (10)  NOT NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_application_classification_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;


COMMENT ON TABLE wkf_application_classification IS 'application classification master';
COMMENT ON COLUMN wkf_application_classification.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_application_classification.company_code IS 'company code';
COMMENT ON COLUMN wkf_application_classification.application_classification_code IS 'application classification code';
COMMENT ON COLUMN wkf_application_classification.application_classification_name IS 'application classification name';
COMMENT ON COLUMN wkf_application_classification.sort_order IS 'sort order';
COMMENT ON COLUMN wkf_application_classification.management_unit_code IS 'management unit code';
COMMENT ON COLUMN wkf_application_classification.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_application_classification.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_application_classification.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_application_classification.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_application_classification_unique_idx1 on wkf_application_classification(company_code,application_classification_code);
