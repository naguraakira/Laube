-- View: wkf_view_appended

-- DROP TABLE wkf_view_appended;

CREATE OR REPLACE VIEW wkf_view_appended AS
 SELECT a.company_code,
    b.company_name,
    a.application_number,
    a.approval_number,
    a.approval_company_code,
    c.company_name as approval_company_name,
    a.approval_unit_code,
    d.unit_name as approval_unit_name,
    a.approval_user_code,
    e.user_name as approval_user_name,
    a.approval_path,
    a.approval_date,
    a.create_date_time,
    a.create_user_id,
    a.update_date_time,
    a.update_user_id
   FROM wkf_appended a
     LEFT JOIN wkf_company b ON a.company_code = b.company_code
     LEFT JOIN wkf_company c ON a.approval_company_code = c.company_code
     LEFT JOIN wkf_unit d ON a.approval_company_code = d.company_code AND a.approval_unit_code = d.unit_code
     LEFT JOIN wkf_user e ON a.approval_company_code = e.company_code AND a.approval_user_code = e.user_code
