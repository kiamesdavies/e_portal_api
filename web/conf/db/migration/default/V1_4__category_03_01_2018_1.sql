CREATE TABLE category (
    category_id character varying(64) NOT NULL,
    category_name character varying(64) NOT NULL,
    date_created timestamp with time zone NOT NULL,
    created_by character varying(64) NOT NULL,
    date_modified timestamp with time zone,
    category_desc text,
    modified_by character varying(64),
    amount double precision NOT NULL,
    PRIMARY KEY (category_id)
);

ALTER TABLE payment
    ADD column amount_to_pay double precision NOT NULL;

ALTER TABLE payment
    ADD column category_id character varying(64) NOT NULL;

ALTER TABLE ONLY payment
    ADD CONSTRAINT payment_category FOREIGN KEY (category_id) REFERENCES category(category_id);

ALTER TABLE payment ALTER COLUMN amount_paid DROP NOT NULL ;


DROP view application_summary;

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
	ps.payment_id,ps.date_paid , ps.bank_name, ps.bank_teller,ps.amount_paid, ps.paid, ps.expiry_date, ps.date_initialized,ps.certificate_path
	
	
from
	app_user a 
left join application_data d on 	a.app_user_id = d.app_user_id
left join (select p.payment_id,  p.date_paid , p.bank_name, p.bank_teller,p.amount_paid , p.app_user_id,p.paid, p.expiry_date, p.date_initialized,p.certificate_path  from payment p INNER JOIN 
     (
       SELECT app_user_id
       FROM payment
       GROUP BY app_user_id
     ) ps2 
     ON (ps2.app_user_id = p.app_user_id)  order by p.date_initialized desc limit 1 ) as ps on a.app_user_id = ps.app_user_id where a.role_name ='TEACHER'; 



DROP TABLE onlie_payment_transaction_raw_reponse;
CREATE TABLE onlie_payment_transaction_raw_reponse(
     raw_response_id serial PRIMARY KEY,
     raw_response json NOT NULL,
     url text NOT NULL,
     request_received_time  timestamp with time zone NOT NULL,
     date_created  timestamp with time zone NOT NULL
);



INSERT INTO public.category (category_id,category_name,date_created,created_by,category_desc,amount)
VALUES ('A','Category A',NOW(),'SYSTEM','Ph.D holders in Education or with Education qualification',5000) ;
INSERT INTO public.category (category_id,category_name,date_created,created_by,category_desc,amount)
VALUES ('B','Category B',NOW(),'SYSTEM','Masters in Education or Masters in other fields with Education qualification',4000) ;
INSERT INTO public.category (category_id,category_name,date_created,created_by,category_desc,amount)
VALUES ('C','Category C',NOW(),'SYSTEM','Degree in Education or Degree in other fields with Education qualification',3500) ;
INSERT INTO public.category (category_id,category_name,date_created,created_by,category_desc,amount)
VALUES ('D','Category D',NOW(),'SYSTEM','NCE',3000) ;
