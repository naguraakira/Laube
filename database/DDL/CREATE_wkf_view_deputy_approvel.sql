-- 
-- CREATE_wkf_view_deputy_approvel.sql
-- 
-- DROP TABLE m_deputy;
-- View: wkf_view_appended

-- DROP TABLE wkf_view_deputy_approvel;

CREATE OR REPLACE VIEW wkf_view_deputy_approvel AS
 SELECT a.company_code,
 b.company_name,
 a.unit_code,
 c.unit_name,
 a.user_code,
 d.user_name,
 a.deputy_approverl_company_code,
 e.company_name as deputy_approverl_company_name,
 a.deputy_approverl_unit_code,
 f.unit_code as deputy_approverl_unit_name,
 a.deputy_approverl_user_code,
 g.user_name as deputy_approverl_user_name,
 a.deputy_contents,
 a.create_date_time,
 a.create_user_id,
 a.update_date_time,
 a.update_user_id
   FROM wkf_deputy_approvel a
     LEFT JOIN wkf_company b ON a.company_code = b.company_code
     LEFT JOIN wkf_unit c ON a.company_code = c.company_code AND a.unit_code = c.unit_code
     LEFT JOIN wkf_user d ON a.company_code = d.company_code AND a.user_code = d.user_code
     LEFT JOIN wkf_company e ON a.deputy_approverl_company_code = e.company_code
     LEFT JOIN wkf_unit f ON a.deputy_approverl_company_code = f.company_code AND a.deputy_approverl_unit_code = f.unit_code
     LEFT JOIN wkf_user g ON a.deputy_approverl_company_code = g.company_code AND a.deputy_approverl_user_code = g.user_code;
