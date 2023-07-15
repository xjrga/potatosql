set schema potatosql;
--schema
call database_schema_insert(0,'Rolodex');
--tables
--table - person
call database_table_insert(0,0,'person');
--table table_keys - person
call table_key_insert(0,0,1,'person_id',0,1,true);
call table_key_insert(0,0,2,'person_name',2,2,false);
--table primary table_keys - person
call primary_key_insert(0,0,1);
--table data table_keys - person
call data_key_insert(0,0,2);
--table - address
call database_table_insert(0,1,'address');
--table table_keys - address
call table_key_insert(0,1,1,'address_id',0,1,true);
call table_key_insert(0,1,2,'address_detail',2,2,false);
--table primary table_keys - address
call primary_key_insert(0,1,1);
--table data table_keys - address
call data_key_insert(0,1,2);
--table - address usage
call database_table_insert(0,2,'address_usage');
--table table_keys - address usage
call table_key_insert(0,2,1,'person_id',0,1,true);
call table_key_insert(0,2,2,'address_id',2,2,true);
call table_key_insert(0,2,3,'address_usage_type',0,3,false);
call table_key_insert(0,2,4,'address_usage_start_date',3,4,false);
--table primary table_keys - address usage
call primary_key_insert(0,2,1);
call primary_key_insert(0,2,2);
--table data table_keys - address usage
call data_key_insert(0,2,3);
call data_key_insert(0,2,4);
--relationship
call relationship_insert(0,0,0,2,true);
call relationship_insert(0,1,1,2,true);
--identifying relationship
call identifying_relationship_insert(0,0,0,2);
call identifying_relationship_insert(0,1,1,2);
--nonidentifying relationship

--identifying relationship key pair
--person - address usage
call identifying_relationship_key_pair_insert(0,0,0,1,2,1);
--address - address usage
call identifying_relationship_key_pair_insert(0,1,1,1,2,2);
--nonidentifying


