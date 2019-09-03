package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.data.DbLink;
import org.xjrga.potatosql.dataobject.SchemaDataObject;

public class Generator1 implements Code
{

    private DbLink dbLink;
    private boolean Tables;
    private int[] TableIds;
    private boolean isHsqldb;
    private boolean isMysql;
    private SchemaDataObject schemaDataObject;

    public Generator1(DbLink dbLink)
    {
        this.dbLink = dbLink;
    }

    public void setTableIds(int[] tableIds)
    {
        this.TableIds = tableIds;
    }

    public void setTablesAndRelationshipsSelected(boolean tables)
    {
        Tables = tables;
    }

    @Override
    public String getCode()
    {
        StringBuilder sb = new StringBuilder();

        if (isHsqldb)
        {
            sb.append(getHsqldb());

        } else if (isMysql)
        {
            sb.append(getMysql());
        }
        return sb.toString();
    }


    public String getHsqldb()
    {
        StringBuilder sb = new StringBuilder();

        TableMaker tableMaker = new TableMaker(dbLink);

        if (Tables)
        {
            for (int i = 0; i < TableIds.length; i++)
            {
                Integer tableId = TableIds[i];
                Table table = tableMaker.getTable(getSchemaDataObject().getSchemaId(), tableId);
                SqlStuff sqlStuff = new SqlStuff(table);

                CreateTable createTable = new CreateTable(table, sqlStuff);
                sb.append(createTable.getCode());
                sb.append("\n");
                sb.append("\n");

            }

            CreateRelationship createRelationship = new CreateRelationship(dbLink, getSchemaDataObject().getSchemaId());
            String code = createRelationship.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");

        }

        return sb.toString();
    }


    public String getMysql()
    {

        StringBuilder sb = new StringBuilder();
        sb.append("Not implemented yet.");
        return sb.toString();
    }


    public void setMysql(boolean mysql)
    {

        isMysql = mysql;
    }


    public void setHsqldb(boolean hsqldb)
    {
        isHsqldb = hsqldb;
    }

    public void setSchemaDataObject(SchemaDataObject schemaDataObject)
    {
           this.schemaDataObject = schemaDataObject;
    }

    public SchemaDataObject getSchemaDataObject()
    {
        return schemaDataObject;
    }

}
