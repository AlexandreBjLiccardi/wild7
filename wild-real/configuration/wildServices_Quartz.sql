PGDMP                         u           postgres    9.6.2    9.6.2     )           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            *           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            	            2615    322472    wild_services    SCHEMA        CREATE SCHEMA wild_services;
    DROP SCHEMA wild_services;
             postgres    false            /           1259    322473    detailed_results    TABLE     �   CREATE TABLE detailed_results (
    id_test_service_executions character varying(255) NOT NULL,
    result_id character varying(255) NOT NULL,
    result_position character varying(255),
    result_type character varying(255),
    result_value text
);
 +   DROP TABLE wild_services.detailed_results;
       wild_services         postgres    false    9            0           1259    322479 	   fa_errors    TABLE     �   CREATE TABLE fa_errors (
    id_test_service_executions character varying(255) NOT NULL,
    error_line bigint NOT NULL,
    error_column bigint NOT NULL,
    error_message text,
    error_code character varying(255) NOT NULL,
    error_level text
);
 $   DROP TABLE wild_services.fa_errors;
       wild_services         postgres    false    9            1           1259    322485    schema_errors    TABLE     �   CREATE TABLE schema_errors (
    id_test_service_executions character varying(255) NOT NULL,
    error_line bigint NOT NULL,
    error_column bigint NOT NULL,
    error_message text,
    error_level text,
    error_code character varying(255) NOT NULL
);
 (   DROP TABLE wild_services.schema_errors;
       wild_services         postgres    false    9            4           1259    322503    test_executions    TABLE     �   CREATE TABLE test_executions (
    id_test_executions character varying(255) NOT NULL,
    date_begin timestamp without time zone,
    date_end timestamp without time zone,
    interrupted boolean,
    fullsize bigint,
    fulltime bigint
);
 *   DROP TABLE wild_services.test_executions;
       wild_services         postgres    false    9            5           1259    322538    test_service_config    TABLE     %  CREATE TABLE test_service_config (
    id_test_service_config character varying(255) NOT NULL,
    sha1 character varying(255) NOT NULL,
    config_file_path text,
    date_file_path timestamp without time zone,
    date_first_path timestamp without time zone,
    intended_services text[]
);
 .   DROP TABLE wild_services.test_service_config;
       wild_services         postgres    false    9            2           1259    322491    test_service_executions    TABLE     �  CREATE TABLE test_service_executions (
    id_test_service_executions character varying(255) NOT NULL,
    id_test_service_config character varying(255) NOT NULL,
    id_test_executions character varying(255),
    method_name text,
    date_begin timestamp without time zone,
    date_end timestamp without time zone,
    interrupted boolean,
    disk_size bigint,
    element_size bigint,
    fulltime bigint,
    success boolean,
    schema_validity boolean,
    schema_error_number bigint,
    business_validity boolean,
    business_error_number bigint,
    requested_url text,
    requested_schema text,
    requested_scenario text,
    response_format text
);
 2   DROP TABLE wild_services.test_service_executions;
       wild_services         postgres    false    9            3           1259    322497    test_service_parameters    TABLE       CREATE TABLE test_service_parameters (
    id_test_service_config character varying(255) NOT NULL,
    parameter_id character varying(255) NOT NULL,
    parameter_position character varying(255),
    parameter_type character varying(255),
    parameter_value text
);
 2   DROP TABLE wild_services.test_service_parameters;
       wild_services         postgres    false    9            �           2606    322507 &   detailed_results detailed_results_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY detailed_results
    ADD CONSTRAINT detailed_results_pkey PRIMARY KEY (id_test_service_executions, result_id);
 W   ALTER TABLE ONLY wild_services.detailed_results DROP CONSTRAINT detailed_results_pkey;
       wild_services         postgres    false    303    303    303            �           2606    322509    fa_errors sandre_errors_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY fa_errors
    ADD CONSTRAINT sandre_errors_pkey PRIMARY KEY (id_test_service_executions, error_line, error_column, error_code);
 M   ALTER TABLE ONLY wild_services.fa_errors DROP CONSTRAINT sandre_errors_pkey;
       wild_services         postgres    false    304    304    304    304    304            �           2606    322511     schema_errors schema_errors_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY schema_errors
    ADD CONSTRAINT schema_errors_pkey PRIMARY KEY (id_test_service_executions, error_line, error_column, error_code);
 Q   ALTER TABLE ONLY wild_services.schema_errors DROP CONSTRAINT schema_errors_pkey;
       wild_services         postgres    false    305    305    305    305    305            �           2606    322513 $   test_executions test_executions_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY test_executions
    ADD CONSTRAINT test_executions_pkey PRIMARY KEY (id_test_executions);
 U   ALTER TABLE ONLY wild_services.test_executions DROP CONSTRAINT test_executions_pkey;
       wild_services         postgres    false    308    308            �           2606    322545 ,   test_service_config test_service_config_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY test_service_config
    ADD CONSTRAINT test_service_config_pkey PRIMARY KEY (id_test_service_config);
 ]   ALTER TABLE ONLY wild_services.test_service_config DROP CONSTRAINT test_service_config_pkey;
       wild_services         postgres    false    309    309            �           2606    322515 4   test_service_executions test_service_executions_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY test_service_executions
    ADD CONSTRAINT test_service_executions_pkey PRIMARY KEY (id_test_service_executions);
 e   ALTER TABLE ONLY wild_services.test_service_executions DROP CONSTRAINT test_service_executions_pkey;
       wild_services         postgres    false    306    306            �           2606    322517 4   test_service_parameters test_service_parameters_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY test_service_parameters
    ADD CONSTRAINT test_service_parameters_pkey PRIMARY KEY (id_test_service_config, parameter_id);
 e   ALTER TABLE ONLY wild_services.test_service_parameters DROP CONSTRAINT test_service_parameters_pkey;
       wild_services         postgres    false    307    307    307            �           2606    322518 A   detailed_results detailed_results_id_test_service_executions_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY detailed_results
    ADD CONSTRAINT detailed_results_id_test_service_executions_fkey FOREIGN KEY (id_test_service_executions) REFERENCES test_service_executions(id_test_service_executions);
 r   ALTER TABLE ONLY wild_services.detailed_results DROP CONSTRAINT detailed_results_id_test_service_executions_fkey;
       wild_services       postgres    false    306    3734    303            �           2606    322523 7   fa_errors sandre_errors_id_test_service_executions_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY fa_errors
    ADD CONSTRAINT sandre_errors_id_test_service_executions_fkey FOREIGN KEY (id_test_service_executions) REFERENCES test_service_executions(id_test_service_executions);
 h   ALTER TABLE ONLY wild_services.fa_errors DROP CONSTRAINT sandre_errors_id_test_service_executions_fkey;
       wild_services       postgres    false    3734    306    304            �           2606    322528 ;   schema_errors schema_errors_id_test_service_executions_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY schema_errors
    ADD CONSTRAINT schema_errors_id_test_service_executions_fkey FOREIGN KEY (id_test_service_executions) REFERENCES test_service_executions(id_test_service_executions);
 l   ALTER TABLE ONLY wild_services.schema_errors DROP CONSTRAINT schema_errors_id_test_service_executions_fkey;
       wild_services       postgres    false    305    3734    306            �           2606    322533 G   test_service_executions test_service_executions_id_test_executions_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY test_service_executions
    ADD CONSTRAINT test_service_executions_id_test_executions_fkey FOREIGN KEY (id_test_executions) REFERENCES test_executions(id_test_executions);
 x   ALTER TABLE ONLY wild_services.test_service_executions DROP CONSTRAINT test_service_executions_id_test_executions_fkey;
       wild_services       postgres    false    308    306    3738            �           2606    322551 O   test_service_executions test_service_executions_id_test_service_executions_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY test_service_executions
    ADD CONSTRAINT test_service_executions_id_test_service_executions_fkey FOREIGN KEY (id_test_service_config) REFERENCES test_service_config(id_test_service_config);
 �   ALTER TABLE ONLY wild_services.test_service_executions DROP CONSTRAINT test_service_executions_id_test_service_executions_fkey;
       wild_services       postgres    false    3740    309    306            �           2606    322546 O   test_service_parameters test_service_parameters_id_test_service_executions_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY test_service_parameters
    ADD CONSTRAINT test_service_parameters_id_test_service_executions_fkey FOREIGN KEY (id_test_service_config) REFERENCES test_service_config(id_test_service_config);
 �   ALTER TABLE ONLY wild_services.test_service_parameters DROP CONSTRAINT test_service_parameters_id_test_service_executions_fkey;
       wild_services       postgres    false    307    309    3740           