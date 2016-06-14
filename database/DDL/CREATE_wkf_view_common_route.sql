--
-- CREATE_wkf_view_common_route.sql
--
-- DROP View wkf_view_common_route;

CREATE VIEW wkf_view_common_route AS 

SELECT 

A.company_code,
B.company_name,
A.common_route_code as route_code,
A.common_route_name as route_name

FROM wkf_common_route AS A
LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code;

COMMENT ON COLUMN wkf_view_common_route.company_code IS 'company code';
COMMENT ON COLUMN wkf_view_common_route.company_name IS 'company name';
COMMENT ON COLUMN wkf_view_common_route.route_code IS 'route code';
COMMENT ON COLUMN wkf_view_common_route.route_name IS 'route name';

