--
-- CREATE_wkf_view_individual_activity.sql
--
-- DROP View wkf_view_individual_activity;

CREATE VIEW wkf_view_individual_activity AS 

SELECT 

A.company_code,
B.company_name,
A.individual_route_code as route_code,
C.individual_route_name as route_name,
A.activity_code,
A.approval_company_code,
D.company_name as approval_company_name,
A.approval_role_code,
E.role_name as approval_role_name,
A.approval_unit_code,
F.unit_name as approval_unit_name,
A.approval_user_code,
G.user_name as approval_user_name,
A.function,
A.party_code,
A.party_code_connector,
A.next_party_code,
A.party_transit_code,
A.party_transit_code_connector,
A.branch_item_name,
A.branch_item,
A.comparison_operator,
A.create_user_id,
A.update_user_id

FROM wkf_individual_activity AS A

LEFT JOIN wkf_company AS B
ON A.company_code = B.company_code

LEFT JOIN wkf_individual_route AS C
ON A.company_code = C.company_code 
AND A.individual_route_code = C.individual_route_code

LEFT JOIN wkf_company AS D
ON A.approval_company_code = D.company_code

LEFT JOIN wkf_role AS E 
ON A.approval_company_code = E.company_code 
AND A.approval_role_code = E.role_code

LEFT JOIN wkf_unit AS F 
ON A.approval_company_code = F.company_code 
AND A.approval_unit_code = F.unit_code

LEFT JOIN wkf_user AS G 
ON A.approval_company_code = G.company_code 
AND A.approval_user_code = G.user_code;

COMMENT ON COLUMN wkf_view_individual_activity.company_code IS 'company code';
COMMENT ON COLUMN wkf_view_individual_activity.company_name IS 'company name';
COMMENT ON COLUMN wkf_view_individual_activity.route_code IS 'individual route code';
COMMENT ON COLUMN wkf_view_individual_activity.route_name IS 'individual route name';
COMMENT ON COLUMN wkf_view_individual_activity.activity_code IS 'activity code';
COMMENT ON COLUMN wkf_view_individual_activity.approval_company_code IS 'approval company code';
COMMENT ON COLUMN wkf_view_individual_activity.approval_company_name IS 'approval_company_name';
COMMENT ON COLUMN wkf_view_individual_activity.approval_role_code IS 'approval role code';
COMMENT ON COLUMN wkf_view_individual_activity.approval_role_name IS 'approval role name';
COMMENT ON COLUMN wkf_view_individual_activity.approval_unit_code IS 'approval unit code';
COMMENT ON COLUMN wkf_view_individual_activity.approval_unit_name IS 'approval unit name';
COMMENT ON COLUMN wkf_view_individual_activity.approval_user_code IS 'approval user code';
COMMENT ON COLUMN wkf_view_individual_activity.approval_user_name IS 'approval user name';
COMMENT ON COLUMN wkf_view_individual_activity.function IS 'function';
COMMENT ON COLUMN wkf_view_individual_activity.party_code IS 'party code';
COMMENT ON COLUMN wkf_view_individual_activity.next_party_code IS 'next party code';
COMMENT ON COLUMN wkf_view_individual_activity.party_transit_code IS 'party transit code';
COMMENT ON COLUMN wkf_view_individual_activity.party_transit_code_connector IS 'party transit code connector';
COMMENT ON COLUMN wkf_view_individual_activity.branch_item_name IS 'branch item name';
COMMENT ON COLUMN wkf_view_individual_activity.branch_item IS 'branch item';
COMMENT ON COLUMN wkf_view_individual_activity.comparison_operator IS 'comparison operator';
COMMENT ON COLUMN wkf_view_individual_activity.create_user_id IS 'create user id';
COMMENT ON COLUMN wkf_view_individual_activity.update_user_id IS 'update user id';

