--
-- CREATE_wkf_view_application_form.sql
--
-- DROP View wkf_view_application_form;

CREATE VIEW wkf_view_application_form AS
SELECT
A.company_code,
B.company_name,
A.application_form_code,
A.application_form_name,
A.application_classification_code,
C.application_classification_name,
A.skip_apply_user,
A.auto_approval_flag,
A.pulling_flag,
A.route_flag

FROM wkf_application_form AS A

LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code

LEFT JOIN wkf_application_classification AS C
ON A.company_code = C.company_code AND A.application_classification_code = C.application_classification_code;

COMMENT ON COLUMN wkf_view_application_form.company_code IS 'company code';
COMMENT ON COLUMN wkf_view_application_form.company_name IS 'company name';
COMMENT ON COLUMN wkf_view_application_form.application_form_code IS 'application form code';
COMMENT ON COLUMN wkf_view_application_form.application_form_name IS 'application form name';
COMMENT ON COLUMN wkf_view_application_form.application_classification_code IS 'application classification code';
COMMENT ON COLUMN wkf_view_application_form.application_classification_name IS 'application classification name';
COMMENT ON COLUMN wkf_view_application_form.auto_approval_flag IS 'auto approval flag';
COMMENT ON COLUMN wkf_view_application_form.pulling_flag IS 'pulling flag';
COMMENT ON COLUMN wkf_view_application_form.route_flag IS 'route flag';

