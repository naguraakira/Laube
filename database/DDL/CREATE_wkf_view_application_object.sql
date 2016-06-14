--
-- CREATE_wkf_view_application_object.sql
--
-- DROP TABLE wkf_view_application_object;

CREATE  VIEW wkf_view_application_object AS
SELECT
A.id,
A.company_code,
B.company_name,
A.application_number,
A.reApplication_number,
C.application_classification_code,
D.application_classification_name,
A.application_form_code,
C.application_form_name,
A.apply_company_code,
E.company_name as apply_company_name,
A.apply_unit_code,
F.unit_name as apply_unit_name,
A.apply_user_code,
G.user_name as apply_user_name,
A.deputy_apply_company_code,
H.company_name as deputy_apply_company_name,
A.deputy_apply_unit_code,
I.unit_name as deputy_apply_unit_name,
A.deputy_apply_user_code,
J.user_name as deputy_apply_user_name,
A.apply_date,
A.application_status,
A.create_date_time,
A.create_user_id,
A.update_date_time,
A.update_user_id

FROM wkf_application_object AS A

LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code

LEFT JOIN wkf_application_form AS C
ON A.company_code = C.company_code AND A.application_form_code = C.application_form_code

LEFT JOIN wkf_application_classification AS D
ON C.company_code = D.company_code AND C.application_classification_code = D.application_classification_code

LEFT JOIN wkf_company AS E
ON A.apply_company_code = E.company_code

LEFT JOIN wkf_unit AS F
ON A.apply_company_code = F.company_code AND A.apply_unit_code = F.unit_code

LEFT JOIN wkf_user AS G
ON A.apply_company_code = G.company_code AND A.apply_user_code = G.user_code

LEFT JOIN wkf_company AS H
ON A.deputy_apply_company_code = H.company_code

LEFT JOIN wkf_unit AS I
ON A.deputy_apply_company_code = I.company_code AND A.deputy_apply_unit_code = I.unit_code

LEFT JOIN wkf_user AS J
ON A.deputy_apply_company_code = J.company_code AND A.deputy_apply_user_code = J.user_code;
