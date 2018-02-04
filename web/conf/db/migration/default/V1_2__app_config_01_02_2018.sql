INSERT INTO app_config (app_config_id, app_config_value, is_available_to_user, is_check, possible_values, app_config_description) VALUES ('TOKEN_EXPIRY_SECONDS', '36000', false, false, NULL, NULL);
INSERT INTO app_config (app_config_id, app_config_value, is_available_to_user, is_check, possible_values, app_config_description) VALUES ('PASSWORD_RECOVERY_EXPIRY_SECONDS', '36000', false, false, NULL, NULL);
INSERT INTO app_config (app_config_id, app_config_value, is_available_to_user, is_check, possible_values, app_config_description) VALUES ('DEFAULT_ROLE', 'TEACHER', false, false, NULL, NULL);


INSERT INTO app_config (app_config_id, app_config_value, is_available_to_user, is_check, possible_values, app_config_description) VALUES ('PIN_EXPIRY_SECONDS', '1800', false, false, NULL, NULL);



INSERT INTO app_config (app_config_id, app_config_value, is_available_to_user, is_check, possible_values, app_config_description) VALUES ('DEFAULT_TEACHER_FORM', 'TEACHER_FORM', false, false, NULL, 'Default forms for user after sign up');

INSERT INTO app_config (app_config_id, app_config_value, is_available_to_user, is_check, possible_values, app_config_description) VALUES ('DEFAULT_OPERATION_FORM', 'OPERATION_FORM', false, false, NULL, 'Default forms for user after sign up');




INSERT INTO public.form (form_id,form_name,date_created,created_by,form_desc)
VALUES ('TEACHER_FORM','Teacher form',now(),'system','Teacher form') ;
INSERT INTO public.form (form_id,form_name,date_created,created_by,form_desc)
VALUES ('OPERATION_FORM','Teacher for operations',now(),'system','Teacher form') ;




INSERT INTO public.app_user (app_user_id,user_name,password,date_created,created_by, role_name)
VALUES ('admin','admin','1000:4916ec96f43952c306c3cfb89a38bfcc2e20cb190bcd532b:eb10bd0ae59650de2016b9b07f26785a4fc9e12c4c7c6ade',now(),'system','ADMIN') ;

