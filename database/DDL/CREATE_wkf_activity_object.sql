--
-- CREATE_wkf_activity_object.sql
--
-- DROP TABLE wkf_activity_object;

CREATE  TABLE wkf_activity_object
 (
  id BIGSERIAL
 ,company_code VARCHAR (10)  NOT NULL
 ,application_number BIGINT NOT NULL
 ,activity_object_code INT NOT NULL
 ,party_code VARCHAR (3)  NULL
 ,party_code_connector INT NOT NULL
 ,route_type INT NOT NULL
 ,approval_company_code VARCHAR(10)  NULL
 ,approval_unit_code VARCHAR(10)  NULL
 ,approval_user_code VARCHAR(10)  NULL
 ,deputy_approval_company_code VARCHAR(10)  NULL
 ,deputy_approval_user_code VARCHAR(18) NULL
 ,deputy_approval_comment VARCHAR(500) NULL
 ,function INT NOT NULL
 ,next_party_code VARCHAR (3)  NULL
 ,party_transit_code VARCHAR (3)  NULL
 ,party_transit_code_connector INT NOT NULL
 ,reaching_date TIMESTAMP(0) NULL
 ,process_date TIMESTAMP(0) NULL
 ,activity_status INT NOT NULL
 ,approval_comment VARCHAR(500) NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (10)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (10)  NOT NULL

 ,CONSTRAINT wkf_activity_object_pkey PRIMARY KEY (id)
 ,CONSTRAINT wkf_activity_object_fkey FOREIGN KEY ( company_code,application_number )
     REFERENCES wkf_application_object ( company_code,application_number )  ON UPDATE CASCADE ON DELETE CASCADE

)
 TABLESPACE pg_default;


COMMENT ON TABLE wkf_activity_object IS 'activity object';
COMMENT ON COLUMN wkf_activity_object.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_activity_object.company_code IS 'company code';
COMMENT ON COLUMN wkf_activity_object.application_number IS 'application number';
COMMENT ON COLUMN wkf_activity_object.activity_object_code IS 'activity object code';
COMMENT ON COLUMN wkf_activity_object.party_code IS 'party code';
COMMENT ON COLUMN wkf_activity_object.party_code_connector IS 'party code connector';
COMMENT ON COLUMN wkf_activity_object.route_type IS 'route type';
COMMENT ON COLUMN wkf_activity_object.approval_company_code IS 'approval company code';
COMMENT ON COLUMN wkf_activity_object.approval_user_code IS 'approval user code';
COMMENT ON COLUMN wkf_activity_object.deputy_approval_company_code IS 'deputy approval company code';
COMMENT ON COLUMN wkf_activity_object.deputy_approval_user_code IS 'deputy approval user code';
COMMENT ON COLUMN wkf_activity_object.deputy_approval_comment IS 'deputy approval comment';
COMMENT ON COLUMN wkf_activity_object.function IS 'function';
COMMENT ON COLUMN wkf_activity_object.next_party_code IS 'next party code';
COMMENT ON COLUMN wkf_activity_object.party_transit_code IS 'party transit code';
COMMENT ON COLUMN wkf_activity_object.party_transit_code_connector IS 'party transit code connector';
COMMENT ON COLUMN wkf_activity_object.reaching_date IS 'reaching date';
COMMENT ON COLUMN wkf_activity_object.process_date IS 'process date';
COMMENT ON COLUMN wkf_activity_object.activity_status IS 'activity status';
COMMENT ON COLUMN wkf_activity_object.approval_comment IS 'approval comment';
COMMENT ON COLUMN wkf_activity_object.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_activity_object.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_activity_object.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_activity_object.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_activity_object_unique_idx1 on wkf_activity_object(company_code,application_number,activity_object_code);
