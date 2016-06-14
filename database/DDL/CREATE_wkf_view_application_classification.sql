--
-- CREATE_wkf_view_application_classification.sql
--
-- DROP View wkf_view_application_classification;

CREATE VIEW wkf_view_application_classification AS
SELECT
A.company_code,
B.company_name,
A.application_classification_code,
A.application_classification_name,
A.sort_order,
A.management_unit_code

FROM wkf_application_classification AS A

LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code;

COMMENT ON COLUMN wkf_view_application_classification.company_code IS 'company code';
COMMENT ON COLUMN wkf_view_application_classification.company_name IS 'company name';
COMMENT ON COLUMN wkf_view_application_classification.application_classification_code IS 'application classification code';
COMMENT ON COLUMN wkf_view_application_classification.application_classification_name IS 'application classification name';
COMMENT ON COLUMN wkf_view_application_classification.sort_order IS 'sort order';
COMMENT ON COLUMN wkf_view_application_classification.management_unit_code IS 'management unit code';

