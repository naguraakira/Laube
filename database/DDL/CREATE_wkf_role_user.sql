--
-- CREATE_wkf_role_user.sql
--
-- DROP TABLE wkf_role_user;

CREATE  TABLE wkf_role_user
 (
  id BIGSERIAL
 ,company_code VARCHAR (10)  NOT NULL
 ,role_code VARCHAR (10)  NOT NULL
 ,unit_code VARCHAR (10)  NOT NULL
 ,user_code VARCHAR (10)  NOT NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL
 ,CONSTRAINT wkf_user_role_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;


COMMENT ON TABLE wkf_role_user IS 'role user master';
COMMENT ON COLUMN wkf_role_user.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_role_user.company_code IS 'company code';
COMMENT ON COLUMN wkf_role_user.role_code IS 'role code';
COMMENT ON COLUMN wkf_role_user.unit_code IS 'unit code';
COMMENT ON COLUMN wkf_role_user.user_code IS 'employee number';
COMMENT ON COLUMN wkf_role_user.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_role_user.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_role_user.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_role_user.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_role_user_unique_idx1 on wkf_role_user(company_code,role_code,user_code);

alter TABLE wkf_role_user
 add CONSTRAINT wkf_role_user_fkey FOREIGN KEY ( company_code,role_code )
     REFERENCES wkf_role ( company_code,role_code )  ON UPDATE CASCADE ON DELETE CASCADE
