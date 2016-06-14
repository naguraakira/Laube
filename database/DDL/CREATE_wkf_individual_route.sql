--
-- CREATE_wkf_individual_route.sql
--
-- DROP TABLE wkf_individual_route;

CREATE  TABLE wkf_individual_route
 (
  id serial
 ,company_code VARCHAR (10)  NOT NULL
 ,individual_route_code VARCHAR (10)  NOT NULL
 ,individual_route_name VARCHAR (30)  NOT NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_individual_route_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;


COMMENT ON TABLE  wkf_individual_route IS 'individual route';
COMMENT ON COLUMN wkf_individual_route.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_individual_route.company_code IS 'company code';
COMMENT ON COLUMN wkf_individual_route.individual_route_code IS 'individual route code';
COMMENT ON COLUMN wkf_individual_route.individual_route_name IS 'individual route name';
COMMENT ON COLUMN wkf_individual_route.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_individual_route.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_individual_route.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_individual_route.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_individual_route_unique_idx1 on wkf_individual_route(company_code,individual_route_code);
