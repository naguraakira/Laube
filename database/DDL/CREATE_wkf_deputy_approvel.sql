-- 
-- CREATE_wkf_deputy_approvel.sql
-- 
-- DROP TABLE m_deputy;

CREATE  TABLE wkf_deputy_approvel
 ( 
  id serial
 ,company_code VARCHAR (10)  NOT NULL
 ,unit_code VARCHAR (10)  NOT NULL
 ,user_code VARCHAR (10)  NOT NULL
 ,deputy_approverl_company_code VARCHAR (10)  NOT NULL
 ,deputy_approverl_unit_code VARCHAR (10)  NOT NULL
 ,deputy_approverl_user_code VARCHAR (10)  NOT NULL
 ,deputy_contents VARCHAR (100)  NOT NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_deputy_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;


COMMENT ON TABLE wkf_deputy_approvel IS 'deputy master';
COMMENT ON COLUMN wkf_deputy_approvel.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_deputy_approvel.company_code IS 'company code';
COMMENT ON COLUMN wkf_deputy_approvel.user_code IS 'employee number';
COMMENT ON COLUMN wkf_deputy_approvel.unit_code IS 'unit code';
COMMENT ON COLUMN wkf_deputy_approvel.deputy_approverl_company_code IS 'deputy approverl company code';
COMMENT ON COLUMN wkf_deputy_approvel.deputy_approverl_unit_code IS 'deputy approverl unit code';
COMMENT ON COLUMN wkf_deputy_approvel.deputy_approverl_user_code IS 'deputy approverl user code';
COMMENT ON COLUMN wkf_deputy_approvel.deputy_contents IS 'deputy contents';
COMMENT ON COLUMN wkf_deputy_approvel.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_deputy_approvel.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_deputy_approvel.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_deputy_approvel.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_deputy_approvel_unique_idx1 on wkf_deputy_approvel(company_code,unit_code,user_code);
