package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.data.DbLink;
import org.xjrga.potatosql.dataobject.DatabaseSchemaDataObject;

public class Generator1 implements Code {
    private final DbLink dbLink;
    private boolean Tables;
    private int[] TableIds;
    private boolean isHsqldb;
    private boolean isMysql;
    private DatabaseSchemaDataObject databaseSchemaDataObject;

    public Generator1(DbLink dbLink) {
        this.dbLink = dbLink;
    }

    public void setTableIds(int[] tableIds) {
        this.TableIds = tableIds;
    }

    public void setTablesAndRelationshipsSelected(boolean tables) {
        Tables = tables;
    }

    @Override
    public String getCode() {
        StringBuilder sb = new StringBuilder();
        if (isHsqldb) {
            sb.append(getHsqldb());
        } else if (isMysql) {
            sb.append(getMysql());
        }
        return sb.toString();
    }

    public String getHsqldb() {
        StringBuilder sb = new StringBuilder();
        TableMaker tableMaker = new TableMaker(dbLink);
        if (Tables) {
            DropSchema dropSchema = new DropSchema();
            dropSchema.setSchemaDataObject(getDatabaseSchemaDataObject());
            sb.append(dropSchema.getCode());
            CreateSchema createSchema = new CreateSchema();
            createSchema.setDatabaseSchemaDataObject(getDatabaseSchemaDataObject());
            sb.append(createSchema.getCode());
            for (int i = 0; i < TableIds.length; i++) {
                Integer tableId = TableIds[i];
                Table table = tableMaker.getTable(getDatabaseSchemaDataObject().getSchemaId(), tableId);
                table.setSchema(getDatabaseSchemaDataObject().getName());
                SqlStuff sqlStuff = new SqlStuff(table);
                CreateTable createTable = new CreateTable(table, sqlStuff);
                sb.append(createTable.getCode());
                sb.append("\n");
                sb.append("\n");
            }
            CreateRelationship createRelationship = new CreateRelationship(dbLink, getDatabaseSchemaDataObject());
            String code = createRelationship.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");
        }
        return sb.toString();
    }

    public void setHsqldb(boolean hsqldb) {
        isHsqldb = hsqldb;
    }

    public String getMysql() {
        StringBuilder sb = new StringBuilder();
        sb.append("Not implemented yet.");
        return sb.toString();
    }

    public void setMysql(boolean mysql) {
        isMysql = mysql;
    }

    public DatabaseSchemaDataObject getDatabaseSchemaDataObject() {
        return databaseSchemaDataObject;
    }

    public void setDatabaseSchemaDataObject(DatabaseSchemaDataObject databaseSchemaDataObject) {
        this.databaseSchemaDataObject = databaseSchemaDataObject;
    }
}
