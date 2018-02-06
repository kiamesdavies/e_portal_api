DROP View application_dashboard;
DROP VIEW application_count;
DROP view application_summary;

create view application_summary as  

select
	a.app_user_id,
	a.date_created,
	d.date_created as application_date_created,
	a.user_name,
	(case WHEN form_data is null then false else true end) as filled_form,
	form_data #>> '{categories,category}' as category,
	form_data #>> '{personal_information,surname}' as sur_name,
	form_data #>> '{personal_information,first_name}' as first_name,
	form_data #>> '{personal_information,middle_name}' as middle_name,
	to_date(form_data #>> '{birth,date_of_birth}', 'YYYY-MM-DD') as date_of_birth,
	form_data #>> '{birth,sex}' as sex,
	form_data #>> '{personal_information,mobile_number}' as mobile_number,
	form_data #>> '{personal_information,e_mail}' as e_mail,
	form_data #>> '{location,nationality}' as nationality,
	COALESCE(NULLIF(form_data #>> '{location,state}', ''), form_data #>> '{location,other_state}')  as state_of_origin,
	form_data #>> '{location,lga}' as lga_of_origin,
	form_data #>> '{present_employment,employer_name}' as current_employer,
	form_data #>> '{present_employment,employer_address}' as current_employer_office_address,
	form_data #>> '{present_employment,duties}' as area_of_specialization,
	form_data #>> '{form_no}' as form_no,
	
	(case WHEN json_typeof((form_data #>> '{career, institutions}')::json)   = 'array' then
	array_to_string(array( SELECT  concat_ws('; ', qualification_array ->> 'institution_atteneded', qualification_array ->> 'institution_date_atteneded')     FROM application_data d2 , json_array_elements((d2.form_data #>> '{career,institutions}')::json) qualification_array   WHERE d2.app_user_id = a.app_user_id   ), ', ') 
	else concat_ws('; ', form_data #>> '{career, institutions,institution_atteneded}', form_data #>> '{career, institutions,institution_date_atteneded}')  end )
	AS institutions_attended,
	
	(case WHEN json_typeof((form_data #>> '{career, institutions}')::json)   = 'array' then
	array_to_string(array( SELECT  concat_ws('; ', qualification_array ->> 'institution_atteneded', qualification_array ->> 'institution_date_atteneded')     FROM application_data d2 , json_array_elements((d2.form_data #>> '{career,institutions}')::json) qualification_array   WHERE d2.app_user_id = a.app_user_id   ), ', ') 
	else concat_ws('; ', form_data #>> '{career, institutions,institution_atteneded}', form_data #>> '{career, institutions,institution_date_atteneded}')  end )
	AS work_experience,
	
	
	(case WHEN json_typeof((form_data #>> '{work_experience_group, work_experience}')::json)   = 'array' then
	array_to_string(array( SELECT  concat_ws('; ', qualification_array ->> 'work_institution', qualification_array ->> 'no_of_years')     FROM application_data d2 , json_array_elements((d2.form_data #>> '{work_experience_group, work_experience}')::json) qualification_array   WHERE d2.app_user_id = a.app_user_id   ), ', ') 
	else concat_ws('; ', form_data #>> '{work_experience_group, work_experience,work_institution}', form_data #>> '{work_experience_group, work_experience,no_of_years}')  end )
	AS qualifications_with_date,


	ps.payment_id,ps.date_paid , ps.bank_name, ps.bank_teller,ps.amount_paid, ps.paid, ps.expiry_date, ps.date_initialized,ps.certificate_path
	
	
from
	app_user a 
left join application_data d on 	a.app_user_id = d.app_user_id
left join (select p.payment_id,  p.date_paid , p.bank_name, p.bank_teller,p.amount_paid , p.app_user_id,p.paid, p.expiry_date, p.date_initialized,p.certificate_path  from payment p  ) as ps on a.app_user_id = ps.app_user_id where a.role_name ='TEACHER'; 



create view application_dashboard as 
SELECT CONCAT(CAST(extract(month from application_date_created) AS VARCHAR(2)),'-',CAST(extract(year from application_date_created) AS VARCHAR(4))) AS date_range, count(app_user_id) AS count, SUM(amount_paid) AS total_amount_paid 
FROM application_summary
WHERE (application_date_created BETWEEN date_trunc('day', NOW() - interval '6 month') AND NOW() )
GROUP BY CONCAT(CAST(extract(month from application_date_created) AS VARCHAR(2)),'-',CAST(extract(year from application_date_created) AS VARCHAR(4))) order by date_range;



create view application_count as 
SELECT count(app_user_id) AS count, count(CASE WHEN paid = true THEN 1 END) AS all_paid, SUM(amount_paid) AS total_amount_paid 
from  application_summary
where application_date_created is not NULL;