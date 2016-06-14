--
-- CREATE_wkf_view_application_form_route.sql
--
-- DROP View wkf_view_application_form_route;

CREATE VIEW wkf_view_application_form_route AS 
SELECT 
A.company_code,
B.company_name,
A.unit_code,
C.unit_name,
A.application_form_code,
D.application_form_name,
D.application_classification_code,
E.application_classification_name,
A.individual_route_code,
F.individual_route_name,
A.common_route_code,
G.common_route_name

FROM wkf_application_form_route AS A

LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code

LEFT JOIN wkf_unit AS C
ON A.company_code = C.company_code AND A.unit_code = C.unit_code

LEFT JOIN wkf_application_form AS D
ON A.company_code = D.company_code AND A.application_form_code = D.application_form_code

LEFT JOIN wkf_application_classification AS E
ON D.company_code = E.company_code AND D.application_classification_code = E.application_classification_code

LEFT JOIN wkf_individual_route AS F
ON A.company_code = F.company_code AND A.individual_route_code = F.individual_route_code

LEFT JOIN wkf_common_route AS G
ON A.company_code = G.company_code AND A.common_route_code = G.common_route_code;

COMMENT ON COLUMN wkf_view_application_form_route.company_code IS 'company code';
COMMENT ON COLUMN wkf_view_application_form_route.company_name IS 'company name';
COMMENT ON COLUMN wkf_view_application_form_route.unit_code IS 'unit code';
COMMENT ON COLUMN wkf_view_application_form_route.unit_name IS 'unit name';
COMMENT ON COLUMN wkf_view_application_form_route.application_form_code IS 'application form code';
COMMENT ON COLUMN wkf_view_application_form_route.application_form_name IS 'application form name';
COMMENT ON COLUMN wkf_view_application_form_route.individual_route_code IS 'individual route code';
COMMENT ON COLUMN wkf_view_application_form_route.individual_route_name IS 'individual route name';
COMMENT ON COLUMN wkf_view_application_form_route.application_classification_code IS 'application classification code';
COMMENT ON COLUMN wkf_view_application_form_route.application_classification_name IS 'application classification name';
COMMENT ON COLUMN wkf_view_application_form_route.common_route_code IS 'common route code';
COMMENT ON COLUMN wkf_view_application_form_route.common_route_name IS 'common route name';

