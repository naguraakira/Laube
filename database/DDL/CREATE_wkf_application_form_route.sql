--
-- CREATE_wkf_application_form_route.sql
--
--         Automatic creation by LettyTools. 2015/10/16 12:30:50
--
-- DROP TABLE wkf_application_form_route;

CREATE  TABLE wkf_application_form_route
 (
  id BIGSERIAL
 ,company_code VARCHAR (10)  NOT NULL
 ,unit_code VARCHAR (10)
 ,application_form_code VARCHAR (10)  NOT NULL
 ,individual_route_code VARCHAR (10)
 ,common_route_code VARCHAR (10)
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_application_form_route_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;


COMMENT ON TABLE wkf_application_form_route IS 'application form route master';
COMMENT ON COLUMN wkf_application_form_route.company_code IS 'company code';
COMMENT ON COLUMN wkf_application_form_route.unit_code IS 'unit code';
COMMENT ON COLUMN wkf_application_form_route.application_form_code IS 'application form code';
COMMENT ON COLUMN wkf_application_form_route.individual_route_code IS 'individual route code';
COMMENT ON COLUMN wkf_application_form_route.common_route_code IS 'common route code';
COMMENT ON COLUMN wkf_application_form_route.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_application_form_route.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_application_form_route.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_application_form_route.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_application_form_route_unique_idx1 on wkf_application_form_route(company_code,unit_code,application_form_code);

alter TABLE wkf_application_form_route
 add CONSTRAINT wkf_application_form_route_fkey FOREIGN KEY ( company_code,application_form_code )
     REFERENCES wkf_application_form ( company_code,application_form_code )  ON UPDATE CASCADE ON DELETE CASCADE
