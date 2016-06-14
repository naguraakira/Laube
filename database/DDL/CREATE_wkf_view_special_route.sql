--
-- CREATE_wkf_view_special_route.sql
--
-- DROP View wkf_view_special_route;

CREATE VIEW wkf_view_special_route AS 

SELECT 

A.company_code,
B.company_name,
A.application_classification_code as route_code,
A.application_classification_name as route_name

FROM wkf_application_classification AS A
LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code;

COMMENT ON COLUMN wkf_view_special_route.company_code IS 'company code';
COMMENT ON COLUMN wkf_view_special_route.company_name IS 'company name';
COMMENT ON COLUMN wkf_view_special_route.route_code IS 'route code';
COMMENT ON COLUMN wkf_view_special_route.route_name IS 'route name';
