--
-- CREATE_wkf_view_user_role.sql
--
-- DROP View "wkf_view_user_role";

CREATE VIEW wkf_view_user_role AS 
SELECT 
A.company_code,
B.company_name,
A.role_code,
C.role_name,
A.unit_code,
D.unit_name,
A.user_code,
E.user_name
FROM wkf_user_role AS A
LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code
LEFT JOIN wkf_role AS C
ON A.company_code = C.company_code AND A.role_code = C.role_code
LEFT JOIN wkf_unit AS D
ON A.company_code = D.company_code AND A.unit_code = D.unit_code
LEFT JOIN wkf_user AS E
ON A.company_code = E.company_code AND A.user_code = E.user_code;

COMMENT ON COLUMN wkf_view_user_role.company_code IS '��ЃR�[�h';
COMMENT ON COLUMN wkf_view_user_role.role_code IS '�����R�[�h';
COMMENT ON COLUMN wkf_view_user_role.role_name IS '������';
COMMENT ON COLUMN wkf_view_user_role.unit_code IS '����R�[�h';
COMMENT ON COLUMN wkf_view_user_role.unit_name IS '���喼';
COMMENT ON COLUMN wkf_view_user_role.user_code IS '�Ј��ԍ�';
COMMENT ON COLUMN wkf_view_user_role.user_name IS '�Ј���';

