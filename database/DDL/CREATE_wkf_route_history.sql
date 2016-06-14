-- 
-- CREATE_wkf_route_history.sql
-- 
-- DROP TABLE wkf_route_history;

CREATE  TABLE wkf_route_history
 ( 
  id serial
 ,company_code VARCHAR (10)  NOT NULL
 ,application_number VARCHAR (10) NOT NULL 
 ,history_number serial
 ,approval_company_code VARCHAR(10) NOT NULL
 ,approval_company_name VARCHAR(30) NOT NULL
 ,approval_unit_code VARCHAR(10) NOT NULL
 ,approval_unit_name VARCHAR(30) NOT NULL
 ,approval_user_code VARCHAR(10) NOT NULL
 ,approval_user_name VARCHAR(30) NOT NULL
 ,deputy_unit_code VARCHAR(10) NOT NULL
 ,deputy_unit_name VARCHAR(30) NOT NULL
 ,deputy_user_code VARCHAR(10) NOT NULL
 ,deputy_user_name VARCHAR(30) NOT NULL
 ,status INT NOT NULL
 ,process_date TIMESTAMP NOT NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_route_history_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;


COMMENT ON TABLE wkf_route_history IS 'route history object';
COMMENT ON COLUMN wkf_route_history.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_route_history.company_code IS 'company code';
COMMENT ON COLUMN wkf_route_history.application_number IS 'application number';
COMMENT ON COLUMN wkf_route_history.history_number IS 'history number';
COMMENT ON COLUMN wkf_route_history.approval_company_code IS 'approval company code';
COMMENT ON COLUMN wkf_route_history.approval_company_name IS 'approval company name';
COMMENT ON COLUMN wkf_route_history.approval_unit_code IS 'approval unit code';
COMMENT ON COLUMN wkf_route_history.approval_unit_name IS 'approval unit name';
COMMENT ON COLUMN wkf_route_history.approval_user_code IS 'approval user code';
COMMENT ON COLUMN wkf_route_history.approval_user_name IS 'approval user name';
COMMENT ON COLUMN wkf_route_history.deputy_unit_code IS 'deputy unit code';
COMMENT ON COLUMN wkf_route_history.deputy_unit_name IS 'deputy unit name';
COMMENT ON COLUMN wkf_route_history.deputy_user_code IS 'deputy user code';
COMMENT ON COLUMN wkf_route_history.deputy_user_name IS 'deputy user name';
COMMENT ON COLUMN wkf_route_history.status IS 'status';
COMMENT ON COLUMN wkf_route_history.process_date IS 'process date';
COMMENT ON COLUMN wkf_route_history.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_route_history.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_route_history.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_route_history.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_route_history_unique_idx1 on wkf_route_history(company_code,application_number,history_number);

alter TABLE wkf_route_history
 add CONSTRAINT wkf_route_history_fkey FOREIGN KEY ( company_code,application_number )
     REFERENCES wkf_application_object ( company_code,application_number )  ON UPDATE CASCADE ON DELETE CASCADE
