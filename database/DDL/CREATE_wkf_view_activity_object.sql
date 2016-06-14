-- Table: wkf_view_activity_object

-- DROP TABLE wkf_view_activity_object;

CREATE  VIEW wkf_view_activity_object AS
SELECT
  A.id,
  A.company_code,
  B.company_name,
  A.application_number,
  A.activity_object_code,
  A.party_code,
  A.party_code_connector,
  A.route_type,
  A.approval_company_code,
  C.company_name as approval_company_name,
  A.approval_unit_code,
  D.unit_name as approval_unit_name,
  A.approval_user_code,
  E.user_name as approval_user_name,
  A.deputy_approval_company_code,
  F.company_name as deputy_approval_company_name,
  A.deputy_approval_user_code,
  G.user_name as deputy_approval_user_name,
  A.deputy_approval_comment,
  A.function,
  A.next_party_code,
  A.party_transit_code,
  A.party_transit_code_connector,
  A.reaching_date,
  A.process_date,
  A.activity_status,
  A.approval_comment,
  A.create_date_time,
  A.create_user_id,
  A.update_date_time,
  A.update_user_id

FROM wkf_activity_object AS A

LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code

LEFT JOIN wkf_company AS C
ON A.approval_company_code = C.company_code

LEFT JOIN wkf_unit AS D
ON A.approval_company_code = D.company_code AND A.approval_unit_code = D.unit_code

LEFT JOIN wkf_user AS E
ON A.approval_company_code = E.company_code AND A.approval_user_code = E.user_code

LEFT JOIN wkf_company AS F
ON A.deputy_approval_company_code = F.company_code

LEFT JOIN wkf_user AS G
ON A.deputy_approval_company_code = G.company_code AND A.deputy_approval_user_code = G.user_code;
