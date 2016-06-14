--
-- CREATE_wkf_view_boss.sql
--
-- DROP View wkf_view_boss;

CREATE VIEW wkf_view_boss AS
SELECT
A.company_code,
B.company_name,
A.unit_code,
C.unit_name,
A.user_code,
D.user_name,
A.application_form_code,
E.application_form_name,
A.boss_company_code,
F.company_name as boss_company_name,
A.boss_unit_code,
G.unit_name as boss_unit_name,
A.boss_user_code,
H.user_name as boss_user_name


FROM wkf_boss AS A

LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code

LEFT JOIN wkf_unit AS C
ON A.company_code = C.company_code AND A.unit_code = C.unit_code

LEFT JOIN wkf_user AS D
ON A.company_code = D.company_code AND A.user_code = D.user_code

LEFT JOIN wkf_application_form AS E
ON A.company_code = E.company_code AND A.application_form_code = E.application_form_code

LEFT JOIN wkf_company AS F
ON A.boss_company_code = F.company_code

LEFT JOIN wkf_unit AS G
ON A.boss_company_code = G.company_code AND A.boss_unit_code = G.unit_code

LEFT JOIN wkf_user AS H
ON A.boss_company_code = H.company_code AND A.boss_user_code = H.user_code;

COMMENT ON COLUMN wkf_view_boss.company_code IS '��ЃR�[�h';
COMMENT ON COLUMN wkf_view_boss.company_name IS '��Ж�';
COMMENT ON COLUMN wkf_view_boss.unit_code IS '����R�[�h';
COMMENT ON COLUMN wkf_view_boss.unit_name IS '���喼';
COMMENT ON COLUMN wkf_view_boss.application_form_code IS '�\�����R�[�h';
COMMENT ON COLUMN wkf_view_boss.application_form_name IS '�\������';
COMMENT ON COLUMN wkf_view_boss.boss_company_code IS '������i�̉�ЃR�[�h';
COMMENT ON COLUMN wkf_view_boss.boss_company_name IS '������i�̉�Ж�';
COMMENT ON COLUMN wkf_view_boss.boss_unit_code IS '������i�̕���R�[�h';
COMMENT ON COLUMN wkf_view_boss.boss_unit_name IS '������i�̕��喼';
COMMENT ON COLUMN wkf_view_boss.boss_user_code IS '������i�̎Ј��ԍ�';
COMMENT ON COLUMN wkf_view_boss.boss_user_name IS '������i�̎Ј���';

