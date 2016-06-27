--
-- CREATE_wkf_role.sql
--
-- DROP TABLE wkf_role;

CREATE  TABLE wkf_role
 (
  id BIGSERIAL
 ,company_code VARCHAR (10)  NOT NULL
 ,role_code VARCHAR (10)  NOT NULL
 ,role_name VARCHAR (30)  NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_role_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;

COMMENT ON TABLE  wkf_role IS 'role master';
COMMENT ON COLUMN wkf_role.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_role.company_code IS 'company code';
COMMENT ON COLUMN wkf_role.role_code IS 'role code';
COMMENT ON COLUMN wkf_role.role_name IS 'role name';
COMMENT ON COLUMN wkf_role.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_role.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_role.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_role.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_role_unique_idx1 on wkf_role(company_code,role_code);
