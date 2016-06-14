--
-- CREATE_wkf_view_role.sql
--
-- DROP View wkf_view_role;

CREATE VIEW wkf_view_role AS 
SELECT 
A.company_code,
B.company_name,
A.role_code,
A.role_name
FROM wkf_role AS A
LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code;

COMMENT ON COLUMN wkf_view_role.company_code IS '会社コード';
COMMENT ON COLUMN wkf_view_role.company_name IS '会社名';
COMMENT ON COLUMN wkf_view_role.role_code IS '役割コード';
COMMENT ON COLUMN wkf_view_role.role_name IS '役割名';

