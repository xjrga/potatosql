CREATE FUNCTION is_it_a_dependent_table (
IN V_Schema_id INTEGER,
IN V_Table_id INTEGER
) RETURNS INTEGER
READS SQL DATA BEGIN ATOMIC
DECLARE v_count INTEGER;
SELECT COUNT(DISTINCT table_id_child) INTO v_count
FROM identifying_relationship
WHERE schema_id = V_Schema_id
AND   table_id_child = V_Table_id;
RETURN v_count;
END;
/
CREATE FUNCTION compare_datatype (
IN v_schema_id INTEGER,
IN v_parent_table_id INTEGER,
IN v_parent_key_id INTEGER,
IN v_child_table_id INTEGER,
IN v_child_key_id INTEGER
) RETURNS BOOLEAN
READS SQL DATA BEGIN ATOMIC
DECLARE v_check BOOLEAN;
SELECT CASE
         WHEN a.datatype_id = b.datatype_id THEN TRUE
         ELSE FALSE
       END
INTO v_check
FROM (SELECT *
      FROM table_key a
      WHERE a.schema_id = v_schema_id
      AND   a.table_id = v_parent_table_id
      AND   a.key_id = v_parent_key_id) a,
     (SELECT *
      FROM table_key a
      WHERE a.schema_id = v_schema_id
      AND   a.table_id = v_child_table_id
      AND   a.key_id = v_child_key_id) b
WHERE a.schema_id = b.schema_id;
RETURN v_check;
END;
/
CREATE PROCEDURE copy_schema (
OUT newid INTEGER,
IN v_schema_id_old INTEGER
)
MODIFIES SQL DATA
BEGIN ATOMIC
DECLARE v_schema_id_new INTEGER;
SET v_schema_id_new = id();
INSERT INTO database_schema
(
  schema_id,
  schema_name
)
SELECT v_schema_id_new,
       concat(schema_name,'_dup')
FROM database_schema
WHERE schema_id = v_schema_id_old;
INSERT INTO database_table
(
  schema_id,
  table_id,
  table_name
)
SELECT v_schema_id_new,
       table_id,
       table_name
FROM database_table
WHERE schema_id = v_schema_id_old;
INSERT INTO table_key
(
  schema_id,
  table_id,
  key_id,
  key_name,
  datatype_id,
  key_order,
  is_primary_key
)
SELECT v_schema_id_new,
       table_id,
       key_id,
       key_name,
       datatype_id,
       key_order,
       is_primary_key
FROM table_key
WHERE schema_id = v_schema_id_old;
INSERT INTO primary_key
(
  schema_id,
  table_id,
  key_id
)
SELECT v_schema_id_new,
       table_id,
       key_id
FROM primary_key
WHERE schema_id = v_schema_id_old;
INSERT INTO data_key
(
  schema_id,
  table_id,
  key_id
)
SELECT v_schema_id_new,
       table_id,
       key_id
FROM data_key
WHERE schema_id = v_schema_id_old;
INSERT INTO relationship
(
  schema_id,
  relationship_id,
  table_id_parent,
  table_id_child,
  is_identifying
)
SELECT v_schema_id_new,
       relationship_id,
       table_id_parent,
       table_id_child,
       is_identifying
FROM relationship
WHERE schema_id = v_schema_id_old;
INSERT INTO identifying_relationship
(
  schema_id,
  relationship_id,
  table_id_parent,
  table_id_child
)
SELECT v_schema_id_new,
       relationship_id,
       table_id_parent,
       table_id_child
FROM identifying_relationship
WHERE schema_id = v_schema_id_old;
INSERT INTO nonidentifying_relationship
(
  schema_id,
  relationship_id,
  table_id_parent,
  table_id_child
)
SELECT v_schema_id_new,
       relationship_id,
       table_id_parent,
       table_id_child
FROM nonidentifying_relationship
WHERE schema_id = v_schema_id_old;
INSERT INTO identifying_relationship_key_pair
(
  schema_id,
  relationship_id,
  table_id_parent,
  key_id_parent,
  table_id_child,
  key_id_child
)
SELECT v_schema_id_new,
       relationship_id,
       table_id_parent,
       key_id_parent,
       table_id_child,
       key_id_child
FROM identifying_relationship_key_pair
WHERE schema_id = v_schema_id_old;
INSERT INTO nonidentifying_relationship_key_pair
(
  schema_id,
  relationship_id,
  table_id_parent,
  key_id_parent,
  table_id_child,
  key_id_child
)
SELECT v_schema_id_new,
       relationship_id,
       table_id_parent,
       key_id_parent,
       table_id_child,
       key_id_child
FROM nonidentifying_relationship_key_pair
WHERE schema_id = v_schema_id_old;
SET newid = v_schema_id_new;
END;
/
CREATE PROCEDURE copy_table (
OUT newid INTEGER,
IN v_schema_id INTEGER,
IN v_table_id INTEGER
)
MODIFIES SQL DATA
BEGIN ATOMIC
DECLARE v_table_id_new INTEGER;
SET v_table_id_new = id();
INSERT INTO database_table
(
  schema_id,
  table_id,
  table_name
)
SELECT schema_id,
       v_table_id_new,
       concat(table_name,'_dup')
FROM database_table
WHERE schema_id = v_schema_id
AND table_id = v_table_id;
INSERT INTO table_key
(
  schema_id,
  table_id,
  key_id,
  key_name,
  datatype_id,
  key_order,
  is_primary_key
)
SELECT schema_id,
       v_table_id_new,
       key_id,
       key_name,
       datatype_id,
       key_order,
       is_primary_key
FROM table_key
WHERE schema_id = v_schema_id
AND table_id = v_table_id;
INSERT INTO primary_key
(
  schema_id,
  table_id,
  key_id
)
SELECT schema_id,
       v_table_id_new,
       key_id
FROM primary_key
WHERE schema_id = v_schema_id
AND table_id = v_table_id;
INSERT INTO data_key
(
  schema_id,
  table_id,
  key_id
)
SELECT schema_id,
       v_table_id_new,
       key_id
FROM data_key
WHERE schema_id = v_schema_id
AND table_id = v_table_id;
SET newid = v_table_id_new;
END;
/
CREATE FUNCTION find_empty_relationships (
--
IN v_schema_id INTEGER
--
)
RETURNS TABLE (
--
relationship_id INTEGER
--
)
--
READS SQL DATA
--
BEGIN ATOMIC
--
RETURN TABLE (
--
SELECT a.relationship_id
FROM identifying_relationship a
  LEFT JOIN identifying_relationship_key_pair b
         ON a.schema_id = b.schema_id
        AND a.relationship_id = b.relationship_id
        AND a.table_id_parent = b.table_id_parent
        AND a.table_id_child = b.table_id_child
        AND schema_id = v_schema_id
WHERE b.key_id_parent IS NULL
UNION
SELECT a.relationship_id
FROM nonidentifying_relationship a
  LEFT JOIN nonidentifying_relationship_key_pair b
         ON a.schema_id = b.schema_id
        AND a.relationship_id = b.relationship_id
        AND a.table_id_parent = b.table_id_parent
        AND a.table_id_child = b.table_id_child
        AND schema_id = v_schema_id
WHERE b.key_id_parent IS NULL
--
);
--
END;
/
