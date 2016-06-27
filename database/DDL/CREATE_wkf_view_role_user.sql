--
-- CREATE_wkf_view_role_user.sql
--
-- DROP View "wkf_view_role_user";

CREATE VIEW wkf_view_role_user AS 
SELECT 
A.company_code,
B.company_name,
A.role_code,
C.role_name,
A.unit_code,
D.unit_name,
A.user_code,
E.user_name
FROM wkf_role_user AS A
LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code
LEFT JOIN wkf_role AS C
ON A.company_code = C.company_code AND A.role_code = C.role_code
LEFT JOIN wkf_unit AS D
ON A.company_code = D.company_code AND A.unit_code = D.unit_code
LEFT JOIN wkf_user AS E
ON A.company_code = E.company_code AND A.user_code = E.user_code;
