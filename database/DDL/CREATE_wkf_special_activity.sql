--
-- CREATE_wkf_special_activity.sql
--
-- DROP TABLE wkf_special_activity;

CREATE  TABLE wkf_special_activity
 (
  id serial
 ,company_code VARCHAR (10)  NOT NULL
 ,special_route_code VARCHAR (10)  NOT NULL
 ,activity_code INT  NOT NULL
 ,approval_company_code VARCHAR (10)  NOT NULL
 ,approval_role_code VARCHAR (10)
 ,approval_unit_code VARCHAR (10)
 ,approval_user_code VARCHAR (10)
 ,function INTEGER NOT NULL
 ,party_code VARCHAR (3)  NOT NULL
 ,party_code_connector INTEGER
 ,next_party_code VARCHAR (3)  NOT NULL
 ,party_transit_code VARCHAR (3)  NOT NULL
 ,party_transit_code_connector INT  NOT NULL
 ,branch_item_name VARCHAR (50)
 ,branch_item INTEGER
 ,comparison_operator INT  NOT NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_special_activity_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;


COMMENT ON TABLE  wkf_special_activity IS 'common activity master';
COMMENT ON COLUMN wkf_special_activity.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_special_activity.company_code IS 'company code';
COMMENT ON COLUMN wkf_special_activity.special_route_code IS 'special route code';
COMMENT ON COLUMN wkf_special_activity.activity_code IS 'activity code';
COMMENT ON COLUMN wkf_special_activity.approval_company_code IS 'approval company code';
COMMENT ON COLUMN wkf_special_activity.approval_role_code IS 'approval role code';
COMMENT ON COLUMN wkf_special_activity.approval_unit_code IS 'approval unit code';
COMMENT ON COLUMN wkf_special_activity.approval_user_code IS 'approval user code';
COMMENT ON COLUMN wkf_special_activity.function IS 'function';
COMMENT ON COLUMN wkf_special_activity.party_code IS 'party code';
COMMENT ON COLUMN wkf_special_activity.party_code_connector IS 'party code connector';
COMMENT ON COLUMN wkf_special_activity.next_party_code IS 'next party code';
COMMENT ON COLUMN wkf_special_activity.party_transit_code IS 'party transit code';
COMMENT ON COLUMN wkf_special_activity.party_transit_code_connector IS 'party transit connector';
COMMENT ON COLUMN wkf_special_activity.branch_item_name IS 'party transit branch item name';
COMMENT ON COLUMN wkf_special_activity.branch_item IS 'party transit branch item';
COMMENT ON COLUMN wkf_special_activity.comparison_operator IS 'party transit comparison operator';
COMMENT ON COLUMN wkf_special_activity.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_special_activity.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_special_activity.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_special_activity.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_special_activity_unique_idx1 on wkf_special_activity(company_code,special_route_code,activity_code);

alter TABLE wkf_special_activity
 add CONSTRAINT wkf_special_activity_fkey FOREIGN KEY ( company_code,special_route_code )
     REFERENCES wkf_application_classification ( company_code,application_classification_code )  ON UPDATE CASCADE ON DELETE CASCADE
