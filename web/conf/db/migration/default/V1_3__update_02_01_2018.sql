
ALTER TABLE app_user
    ADD column registration_date timestamp with time zone NOT null  DEFAULT NOW();

ALTER TABLE manual_transaction
    ADD column bank_name character varying(128);
ALTER TABLE manual_transaction
    ADD column bank_teller character varying(128);
    
ALTER TABLE manual_transaction
    ADD column date_paid timestamp with time zone;
    

ALTER TABLE payment
    ADD column bank_name character varying(128);
ALTER TABLE payment
    ADD column bank_teller character varying(128);

ALTER TABLE payment
    ADD column certificate_path character varying(256);  
  
ALTER TABLE online_transaction
    ADD column rrr character varying(64);




create view application_summary as  
select
	a.app_user_id,
	a.date_created,
	a.user_name,
	(case WHEN form_data is null then false else true end) as filled_form,
	form_data #>> '{category}' as category,
	form_data #>> '{sur_name}' as sur_name,
	form_data #>> '{first_name}' as first_name,
	form_data #>> '{middle_name}' as middle_name,
	to_date(form_data #>> '{date_of_birth}', 'YYYY-MM-DD') as date_of_birth,
	form_data #>> '{sex}' as sex,
	form_data #>> '{mobile_number}' as mobile_number,
	form_data #>> '{e_mail}' as e_mail,
	form_data #>> '{nationality}' as nationality,
	form_data #>> '{state_of_origin}' as state_of_origin,
	form_data #>> '{lga_of_origin}' as lga_of_origin,
	form_data #>> '{current_employer}' as current_employer,
	form_data #>> '{current_employer_office_address}' as current_employer_office_address,
	form_data #>> '{area_of_specialization}' as area_of_specialization,
	form_data #>> '{form_no}' as form_no,
	form_data #>> '{institutions_attended}' as institutions_attended,
	form_data #>> '{work_experience}' as work_experience,
	array_to_string(array( SELECT  concat_ws('; ', qualification_array ->> 'qualification', qualification_array ->> 'qualification_date')     FROM application_data d2 , json_array_elements((d2.form_data #>> '{qualifications}')::json) qualification_array   WHERE d2.app_user_id = a.app_user_id  AND  json_typeof((form_data #>> '{qualifications}')::json)   = 'array'  ), ', ') AS qualifications_with_date,
	ps.date_paid , ps.bank_name, ps.bank_teller,ps.amount_paid, ps.paid, ps.expiry_date, ps.date_initialized,ps.certificate_path
	
	
from
	app_user a 
left join application_data d on 	a.app_user_id = d.app_user_id
left join (select p.date_paid , p.bank_name, p.bank_teller,p.amount_paid , p.app_user_id,p.paid, p.expiry_date, p.date_initialized,p.certificate_path  from payment p INNER JOIN 
     (
       SELECT app_user_id
       FROM payment
       GROUP BY app_user_id
     ) ps2 
     ON (ps2.app_user_id = p.app_user_id)  order by p.date_initialized desc limit 1 ) as ps on a.app_user_id = ps.app_user_id;



DROP TABLE form_version;
CREATE TABLE form_version (
    form_version_id character varying(64) NOT NULL,
    date_created timestamp with time zone NOT NULL,
    created_by character varying(64) NOT NULL,
    form_id character varying(64) NOT NULL,
    xform_structure xml NOT NULL,
    json_structure json NOT NULL,
    preview_url character varying(128) NOT NULL,
    PRIMARY KEY (form_version_id)
);

ALTER TABLE ONLY form_version
    ADD CONSTRAINT form_form_version FOREIGN KEY (form_id) REFERENCES form(form_id);


  
ALTER TABLE application_data
    ADD form_version_id character varying(64) NOT NULL;


ALTER TABLE ONLY application_data
    ADD CONSTRAINT application_data_form_version FOREIGN KEY (form_version_id) REFERENCES form_version(form_version_id);