CREATE TABLE sms_log(
     sms_log_id serial PRIMARY KEY,
     subject character varying(20)  NULL,
     status character varying(10)  NULL,
     body text ,
     date_created  timestamp with time zone 
);


INSERT INTO app_config (app_config_id, app_config_value, is_available_to_user, is_check, possible_values, app_config_description) VALUES ('RENEWAL_NOTIFICATION_DATES','30,7' , false, false, NULL, 'Comma seperated days that they teacher should be notififed before plan has expired');




INSERT INTO public.message_template
(message_template_id, message_template_name, message_template_description, mail_template, sms_template)
VALUES('PIN_RESET', 'PIN_RESET', 'Pin Reset for password recovery',  NULL, XML('<sms>Hi ${name}, use this pin ${pin} to recover your password. This pin will expire on ${expiryDate}</sms>') );

INSERT INTO public.message_template
(message_template_id, message_template_name, message_template_description, mail_template, sms_template)
VALUES('PAYMENT_CLAIMED', 'PAYMENT_CLAIMED', 'Manual Transaction is successfully claimed',  NULL, XML('<sms>Hi ${name}, you payment has been validated. Log in at ${APP_SERVER_URL} to download your certificate with number ${teacherRegNumber}.</sms>') );

INSERT INTO public.message_template
(message_template_id, message_template_name, message_template_description, mail_template, sms_template)
VALUES('EXPIRED_PAYMENT', 'EXPIRED_PAYMENT', 'Message to be sent days before payment expired',  NULL, XML('<sms>Hi ${name}, your teacher certificate with number ${teacherRegNumber} will expire in the next ${days}(s). You will be notified when to pay for renewal. </sms>') );

INSERT INTO public.message_template
(message_template_id, message_template_name, message_template_description, mail_template, sms_template)
VALUES('PAYMENT_RENEWAL_STARTED', 'PAYMENT_RENEWAL_STARTED', 'Message when payment renewal has started',  NULL, XML('<sms>Hi ${name} your teacher certificate with number ${teacherRegNumber} has expired please log in to ${APP_SERVER_URL} to renew</sms>') );


