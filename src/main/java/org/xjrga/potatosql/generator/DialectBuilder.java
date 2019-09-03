package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.data.DbLink;

public class DialectBuilder implements Code
{

    private DbLink dbLink;
    private boolean Tables;
    private int SchemaId;
    private int[] TableIds;
    private boolean isHsqldb;
    private boolean isMysql;

    public DialectBuilder(DbLink dbLink)
    {
        this.dbLink = dbLink;
    }

    public int getSchemaId()
    {
        return SchemaId;
    }

    public void setSchemaId(int schemaId)
    {
        SchemaId = schemaId;
    }

    public void setTableIds(int[] tableIds)
    {
        this.TableIds = tableIds;
    }

    public void setTables(boolean tables)
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
                Table table = tableMaker.getTable(SchemaId, tableId);
                SqlStuff sqlStuff = new SqlStuff(table);

                CreateTable createTable = new CreateTable(table, sqlStuff);
                sb.append(createTable.getCode());
                sb.append("\n");
                sb.append("\n");

            }

            CreateRelationship createRelationship = new CreateRelationship(dbLink, SchemaId);
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
}
