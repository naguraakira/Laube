--
-- CREATE_wkf_view_individual_route.sql
--
-- DROP View wkf_view_individual_route;

CREATE VIEW wkf_view_individual_route AS 

SELECT 

A.company_code,
B.company_name,
A.individual_route_code as route_code,
A.individual_route_name as route_name

FROM wkf_individual_route AS A
LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code;

COMMENT ON COLUMN wkf_view_individual_route.company_code IS 'company code';
COMMENT ON COLUMN wkf_view_individual_route.company_name IS 'company name';
COMMENT ON COLUMN wkf_view_individual_route.route_code IS 'route code';
COMMENT ON COLUMN wkf_view_individual_route.route_name IS 'route name';

