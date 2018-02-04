CREATE TABLE app_user (
    app_user_id character varying(64) NOT NULL,
    first_name character varying(64),
    last_name character varying(64),
    user_name character varying(64)  NOT NULL,
    password character varying(128) NOT NULL,
    mobile_number character varying(15),
    date_created timestamp with time zone NOT NULL,
    created_by character varying(64)  NOT NULL,
    date_modified timestamp with time zone,
    modified_by character varying(64),
    role_name character varying(64) NOT NULL,
    active boolean,
    PRIMARY KEY (app_user_id)
);

ALTER TABLE ONLY app_user
    ADD constraint app_user_user_name_unique UNIQUE (user_name);


CREATE TABLE form (
    form_id character varying(64) NOT NULL,
    form_name character varying(64) NOT NULL,
    date_created timestamp with time zone NOT NULL,
    created_by character varying(64) NOT NULL,
    date_modified timestamp with time zone,
    form_desc text,
    modified_by character varying(64),
    PRIMARY KEY (form_id)
);

CREATE TABLE form_version (
    form_version_id character varying(64) NOT NULL,
    date_created timestamp with time zone NOT NULL,
    created_by character varying(64) NOT NULL,
    form_id character varying(64) NOT NULL,
    xform_structure xml NOT NULL,
    json_structure json NOT NULL,
    preview_url character varying(128) NOT NULL,
    PRIMARY KEY (form_id)
);

ALTER TABLE ONLY form_version
    ADD CONSTRAINT form_form_version FOREIGN KEY (form_id) REFERENCES form(form_id);


CREATE TABLE application_data (
    app_user_id character varying(64) NOT NULL,
    date_created timestamp with time zone NOT NULL,
    created_by character varying(64) NOT NULL,
    form_data jsonb NOT NULL,
    xml_form_data xml NULL,
    PRIMARY KEY (app_user_id)
);

ALTER TABLE ONLY application_data
    ADD CONSTRAINT application_data_app_user FOREIGN KEY (app_user_id) REFERENCES app_user(app_user_id);



CREATE TABLE payment (
    payment_id character varying(64) NOT NULL,
    app_user_id character varying(64) NOT NULL,
    date_initialized timestamp with time zone NOT NULL,
    date_paid timestamp with time zone ,
    expiry_date timestamp with time zone ,
    paid boolean NOT NULL,
    amount_paid double precision NOT NULL,
    payment_type character varying(6) NOT NULL,
    PRIMARY KEY (payment_id)
);


ALTER TABLE ONLY payment
    ADD CONSTRAINT payment_app_user FOREIGN KEY (app_user_id) REFERENCES app_user(app_user_id);



CREATE TABLE online_transaction (
    transaction_id character varying(64) NOT NULL,
    payment_id character varying(64) NOT NULL,
    date_initialized timestamp with time zone NOT NULL,
    amount_to_pay double precision NOT NULL,
    amount_paid double precision,
    date_payed timestamp with time zone,
    sucessful boolean,
    credited_account boolean,
    response_message character varying(256),
    response_code character varying(10),
    PRIMARY KEY (transaction_id)
);

ALTER TABLE ONLY online_transaction
    ADD CONSTRAINT online_transaction_payment FOREIGN KEY (payment_id) REFERENCES payment(payment_id);

CREATE TABLE onlie_payment_transaction_raw_reponse(
     raw_response_id serial PRIMARY KEY,
     raw_response json NOT NULL,
     url text NOT NULL,
     date_created  timestamp with time zone
);


CREATE TABLE manual_transaction (
    transaction_id character varying(64) NOT NULL,
    registration_number character varying(20) NOT NULL,
    payment_id character varying(64)  NULL,
    date_created timestamp with time zone NOT NULL,
    created_by_app_user_id character varying(64) NOT NULL,
    amount_paid double precision,
    date_claimed timestamp with time zone,
    claimed boolean NOT NULL,
    credited_account boolean,
    processing_message text,
    PRIMARY KEY (transaction_id)
);

ALTER TABLE ONLY manual_transaction
    ADD CONSTRAINT manual_transaction_payment FOREIGN KEY (payment_id) REFERENCES payment(payment_id);

ALTER TABLE ONLY manual_transaction
    ADD CONSTRAINT manual_transaction_app_user FOREIGN KEY (created_by_app_user_id) REFERENCES app_user(app_user_id);



CREATE TABLE message_template (
    message_template_id character varying(64) NOT NULL,
    message_template_name character varying(64) NOT NULL,
    message_template_description text,
    mail_template xml,
    sms_template xml,
    PRIMARY KEY (message_template_id)
);

CREATE TABLE app_config (
    app_config_id character varying(64) NOT NULL,
    app_config_value text NOT NULL,
    app_config_name character varying(256) NULL,
    is_available_to_user boolean NOT NULL,
    is_check boolean NOT NULL,
    possible_values text,
    app_config_description text,
    PRIMARY KEY (app_config_id)
);

CREATE TABLE pin_request (
    pin_id character varying(8) NOT NULL,
    app_user_id character varying(64) NOT NULL,
    used boolean NOT NULL,
    date_created timestamp with time zone NOT NULL,
    date_used timestamp with time zone,
    PRIMARY KEY (pin_id)
);

ALTER TABLE ONLY pin_request
    ADD CONSTRAINT pin_request_app_user FOREIGN KEY (app_user_id) REFERENCES app_user(app_user_id);



ALTER TABLE manual_transaction
    ADD COLUMN receipt character varying(64)  NULL;


ALTER TABLE ONLY manual_transaction
    ADD constraint  manual_transaction_receipt_unique UNIQUE (receipt);