package io.github.xjrga.potatosql.generator;

import io.github.xjrga.potatosql.data.DbLink;

public class DialectBuilder implements Code {

    private DbLink dbLink;
    private boolean DataObject;
    private boolean TriggerStatementAfterInsert;
    private boolean TriggerStatementAfterUpdate;
    private boolean TriggerStatementAfterDelete;
    private boolean TriggerRowBeforeInsert;
    private boolean TriggerRowBeforeUpdate;
    private boolean TriggerRowBeforeDelete;
    private boolean TriggerRowAfterInsert;
    private boolean TriggerRowAfterUpdate;
    private boolean TriggerRowAfterDelete;
    private boolean Views;
    private boolean TestClass;
    private boolean Tables;
    private boolean StmtInsert;
    private boolean StmtUpdate;
    private boolean StmtDelete;
    private boolean StmtMerge;
    private boolean StmtSelect;
    private boolean StmtDeleteAll;
    private boolean StmtSelectAll;
    private boolean StmtCount;
    private boolean StmtCreateSelect;
    private boolean StmtInsertSelect;
    private boolean ProcInsert;
    private boolean ProcUpdate;
    private boolean ProcDelete;
    private boolean ProcMerge;
    private boolean ProcSelect;
    private boolean ProcDeleteAll;
    private boolean ProcSelectAll;
    private boolean FunctionCount;
    private boolean MethodInsert;
    private boolean MethodUpdate;
    private boolean MethodDelete;
    private boolean MethodMerge;
    private boolean MethodSelect;
    private boolean MethodDeleteAll;
    private boolean MethodSelectAll;
    private boolean MethodFunctionCount;
    private boolean MethodSelectPrint;
    private boolean MethodSelectAllPrint;
    private boolean MethodFunctionCountPrint;
    private int SchemaId;
    private int[] TableIds;
    private boolean isHsqldb;
    private boolean isMysql;
    private boolean TablesDup;
    private CreateTestClass createTestClass;
    private String schemaName;

    public DialectBuilder(DbLink dbLink) {
        this.dbLink = dbLink;
    }

    public void setIds(int schemaId, int[] tableIds) {
        this.SchemaId = schemaId;
        this.TableIds = tableIds;
    }

    public void setTriggerStatementAfterUpdate(boolean triggerStatementAfterUpdate) {
        TriggerStatementAfterUpdate = triggerStatementAfterUpdate;
    }

    public void setTriggerStatementAfterDelete(boolean triggerStatementAfterDelete) {
        TriggerStatementAfterDelete = triggerStatementAfterDelete;
    }

    public void setTriggerRowBeforeInsert(boolean triggerRowBeforeInsert) {
        TriggerRowBeforeInsert = triggerRowBeforeInsert;
    }

    public void setTriggerRowBeforeUpdate(boolean triggerRowBeforeUpdate) {
        TriggerRowBeforeUpdate = triggerRowBeforeUpdate;
    }

    public void setTriggerRowBeforeDelete(boolean triggerRowBeforeDelete) {
        TriggerRowBeforeDelete = triggerRowBeforeDelete;
    }

    public void setTriggerRowAfterInsert(boolean triggerRowAfterInsert) {
        TriggerRowAfterInsert = triggerRowAfterInsert;
    }

    public void setTriggerRowAfterUpdate(boolean triggerRowAfterUpdate) {
        TriggerRowAfterUpdate = triggerRowAfterUpdate;
    }

    public void setTriggerRowAfterDelete(boolean triggerRowAfterDelete) {
        TriggerRowAfterDelete = triggerRowAfterDelete;
    }

    public void setViews(boolean views) {
        Views = views;
    }

    public void setTestClass(boolean testClass) {
        TestClass = testClass;
    }

    public void setTables(boolean tables) {
        Tables = tables;
    }

    public void setStmtInsert(boolean stmtInsert) {
        StmtInsert = stmtInsert;
    }

    public void setStmtUpdate(boolean stmtUpdate) {
        StmtUpdate = stmtUpdate;
    }

    public void setStmtDelete(boolean stmtDelete) {
        StmtDelete = stmtDelete;
    }

    public void setStmtMerge(boolean stmtMerge) {
        StmtMerge = stmtMerge;
    }

    public void setStmtSelect(boolean stmtSelect) {
        StmtSelect = stmtSelect;
    }

    public void setStmtDeleteAll(boolean stmtDeleteAll) {
        StmtDeleteAll = stmtDeleteAll;
    }

    public void setStmtSelectAll(boolean stmtSelectAll) {
        StmtSelectAll = stmtSelectAll;
    }

    public void setStmtCount(boolean stmtCount) {
        StmtCount = stmtCount;
    }

    public void setStmtCreateSelect(boolean stmtCreateSelect) {
        StmtCreateSelect = stmtCreateSelect;
    }

    public void setProcInsert(boolean procInsert) {
        ProcInsert = procInsert;
    }

    public void setProcUpdate(boolean procUpdate) {
        ProcUpdate = procUpdate;
    }

    public void setProcDelete(boolean procDelete) {
        ProcDelete = procDelete;
    }

    public void setProcMerge(boolean procMerge) {
        ProcMerge = procMerge;
    }

    public void setProcSelect(boolean procSelect) {
        ProcSelect = procSelect;
    }

    public void setProcDeleteAll(boolean procDeleteAll) {
        ProcDeleteAll = procDeleteAll;
    }

    public void setProcSelectAll(boolean procSelectAll) {
        ProcSelectAll = procSelectAll;
    }

    public void setFunctionCount(boolean functionCount) {
        FunctionCount = functionCount;
    }

    public void setMethodInsert(boolean methodInsert) {
        MethodInsert = methodInsert;
    }

    public void setMethodUpdate(boolean methodUpdate) {
        MethodUpdate = methodUpdate;
    }

    public void setMethodDelete(boolean methodDelete) {
        MethodDelete = methodDelete;
    }

    public void setMethodMerge(boolean methodMerge) {
        MethodMerge = methodMerge;
    }

    public void setMethodSelect(boolean methodSelect) {
        MethodSelect = methodSelect;
    }

    public void setMethodDeleteAll(boolean methodDeleteAll) {
        MethodDeleteAll = methodDeleteAll;
    }

    public void setMethodSelectAll(boolean methodSelectAll) {
        MethodSelectAll = methodSelectAll;
    }

    public void setMethodFunctionCount(boolean methodFunctionCount) {
        MethodFunctionCount = methodFunctionCount;
    }

    public void setTriggerStatementAfterInsert(boolean triggerStatementAfterInsert) {
        TriggerStatementAfterInsert = triggerStatementAfterInsert;
    }

    public void setDataObject(boolean dataObject) {
        DataObject = dataObject;
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

        if (TestClass) {
            createTestClass = new CreateTestClass();
        }

        if (Tables) {
            DropSchema dropSchema = new DropSchema(this.getSchemaName());
            sb.append(dropSchema.getCode());
            CreateSchema createSchema = new CreateSchema(this.getSchemaName());
            sb.append(createSchema.getCode());
            SetSchema setSchema = new SetSchema(this.getSchemaName());
            sb.append(setSchema.getCode());
        }

        for (int i = 0; i < TableIds.length; i++) {
            Integer tableId = TableIds[i];
            Table table = tableMaker.getTable(SchemaId, tableId);
            SqlStuff sqlStuff = new SqlStuff(table);

            if (Tables) {
                CreateTable createTable = new CreateTable(table, sqlStuff);
                sb.append(createTable.getCode());
            }
        }

        if (Tables) {
            CreateRelationship createRelationship = new CreateRelationship(dbLink, SchemaId);
            sb.append(createRelationship.getCode());
        }

        //Add triggers
        if (ProcInsert | ProcUpdate | ProcDelete | ProcMerge | ProcSelect | ProcDeleteAll | ProcSelectAll | FunctionCount | TriggerRowBeforeInsert | TriggerRowBeforeUpdate | TriggerRowBeforeDelete | TriggerRowAfterInsert | TriggerRowAfterUpdate | TriggerRowAfterDelete | TriggerStatementAfterInsert | TriggerStatementAfterUpdate | TriggerStatementAfterDelete) {
            SetSchema setSchema = new SetSchema(this.getSchemaName());
            sb.append(setSchema.getCode());
            sb.setLength(sb.length() - 1);
            sb.append("/\n\n");
        }

        for (int i = 0; i < TableIds.length; i++) {
            Integer tableId = TableIds[i];
            Table table = tableMaker.getTable(SchemaId, tableId);
            SqlStuff sqlStuff = new SqlStuff(table);
            TriggerStuff triggerStuff = new TriggerStuff(table);

            if (ProcInsert) {
                ProcedureInsert procedureInsert = new ProcedureInsert(table, sqlStuff);
                sb.append(procedureInsert.getCode());
            }

            if (ProcUpdate) {
                ProcedureUpdate procedureUpdate = new ProcedureUpdate(table, sqlStuff);
                sb.append(procedureUpdate.getCode());
            }

            if (ProcDelete) {
                ProcedureDelete procedureDelete = new ProcedureDelete(table, sqlStuff);
                sb.append(procedureDelete.getCode());
            }

            if (ProcMerge) {
                ProcedureMerge procedureMerge = new ProcedureMerge(table, sqlStuff);
                sb.append(procedureMerge.getCode());
            }

            if (ProcSelect) {
                ProcedureSelect procedureSelect = new ProcedureSelect(table, sqlStuff);
                sb.append(procedureSelect.getCode());
            }

            if (ProcDeleteAll) {
                ProcedureDeleteAll procedureDeleteAll = new ProcedureDeleteAll(table, sqlStuff);
                sb.append(procedureDeleteAll.getCode());
            }

            if (ProcSelectAll) {
                ProcedureSelectAll procedureSelectAll = new ProcedureSelectAll(table, sqlStuff);
                sb.append(procedureSelectAll.getCode());
            }

            if (FunctionCount) {
                FunctionCount functionCount = new FunctionCount(table);
                sb.append(functionCount.getCode());
            }

            if (TriggerRowBeforeInsert) {
                TriggerRowBeforeInsert triggerRowBeforeInsert = new TriggerRowBeforeInsert(table, triggerStuff);
                sb.append(triggerRowBeforeInsert.getCode());
            }

            if (TriggerRowBeforeUpdate) {
                TriggerRowBeforeUpdate triggerRowBeforeUpdate = new TriggerRowBeforeUpdate(table, triggerStuff);
                sb.append(triggerRowBeforeUpdate.getCode());
            }

            if (TriggerRowBeforeDelete) {
                TriggerRowBeforeDelete triggerRowBeforeDelete = new TriggerRowBeforeDelete(table, triggerStuff);
                sb.append(triggerRowBeforeDelete.getCode());
            }

            if (TriggerRowAfterInsert) {
                TriggerRowAfterInsert triggerRowAfterInsert = new TriggerRowAfterInsert(table, triggerStuff);
                sb.append(triggerRowAfterInsert.getCode());
            }

            if (TriggerRowAfterUpdate) {
                TriggerRowAfterUpdate triggerRowAfterUpdate = new TriggerRowAfterUpdate(table, triggerStuff);
                sb.append(triggerRowAfterUpdate.getCode());
            }

            if (TriggerRowAfterDelete) {
                TriggerRowAfterDelete triggerRowAfterDelete = new TriggerRowAfterDelete(table, triggerStuff);
                sb.append(triggerRowAfterDelete.getCode());
            }

            if (TriggerStatementAfterInsert) {
                TriggerStatementAfterInsert triggerStatementAfterInsert = new TriggerStatementAfterInsert(table, triggerStuff);
                sb.append(triggerStatementAfterInsert.getCode());
            }

            if (TriggerStatementAfterUpdate) {
                TriggerStatementAfterUpdate triggerStatementAfterUpdate = new TriggerStatementAfterUpdate(table, triggerStuff);
                sb.append(triggerStatementAfterUpdate.getCode());
            }

            if (TriggerStatementAfterDelete) {
                TriggerStatementAfterDelete triggerStatementAfterDelete = new TriggerStatementAfterDelete(table, triggerStuff);
                sb.append(triggerStatementAfterDelete.getCode());
            }

        }

        if (TablesDup | Views | StmtCreateSelect) {
            SetSchema setSchema = new SetSchema(this.getSchemaName());
            sb.append(setSchema.getCode());
        }

        for (int i = 0; i < TableIds.length; i++) {
            Integer tableId = TableIds[i];
            Table table = tableMaker.getTable(SchemaId, tableId);
            SqlStuff sqlStuff = new SqlStuff(table);

            if (TablesDup) {
                CreateTableDup createTableDup = new CreateTableDup(table, sqlStuff);
                sb.append(createTableDup.getCode());
            }

            if (Views) {
                CreateView createView = new CreateView(table, sqlStuff);
                sb.append(createView.getCode());
            }

            if (StmtCreateSelect) {
                CreateTableUsingSelect createTableUsingSelect = new CreateTableUsingSelect(table, sqlStuff);
                sb.append(createTableUsingSelect.getCode());
            }

        }

        for (int i = 0; i < TableIds.length; i++) {
            Integer tableId = TableIds[i];
            Table table = tableMaker.getTable(SchemaId, tableId);
            SqlStuff sqlStuff = new SqlStuff(table);
            DataObjectStuff dataObjectStuff = new DataObjectStuff(table);

            if (StmtInsert) {
                StatementInsert statementInsert = new StatementInsert(table, sqlStuff);
                sb.append(statementInsert.getCode());
            }

            if (StmtUpdate) {
                StatementUpdate statementUpdate = new StatementUpdate(table, sqlStuff);
                sb.append(statementUpdate.getCode());
            }

            if (StmtDelete) {
                StatementDelete statementDelete = new StatementDelete(table, sqlStuff);
                sb.append(statementDelete.getCode());
            }

            if (StmtMerge) {
                StatementMerge statementMerge = new StatementMerge(table, sqlStuff);
                sb.append(statementMerge.getCode());
            }

            if (StmtSelect) {
                StatementSelect statementSelect = new StatementSelect(table, sqlStuff);
                sb.append(statementSelect.getCode());
            }

            if (StmtDeleteAll) {
                StatementDeleteAll statementDeleteAll = new StatementDeleteAll(table, sqlStuff);
                sb.append(statementDeleteAll.getCode());
            }

            if (StmtSelectAll) {
                StatementSelectAll statementSelectAll = new StatementSelectAll(table, sqlStuff);
                sb.append(statementSelectAll.getCode());
            }

            if (StmtCount) {
                StatementSelectCount statementSelectCount = new StatementSelectCount(table);
                sb.append(statementSelectCount.getCode());
            }

            if (StmtInsertSelect) {
                StatementInsertUsingSelect statementInsertUsingSelect = new StatementInsertUsingSelect(table, sqlStuff);
                sb.append(statementInsertUsingSelect.getCode());
            }

            if (MethodInsert) {
                MethodProcedureInsert methodProcedureInsert = new MethodProcedureInsert(table);
                String code = methodProcedureInsert.getCode();
                sb.append(code);
            }

            if (MethodUpdate) {
                MethodProcedureUpdate methodProcedureUpdate = new MethodProcedureUpdate(table);
                String code = methodProcedureUpdate.getCode();
                sb.append(code);
            }

            if (MethodDelete) {
                MethodProcedureDelete methodProcedureDelete = new MethodProcedureDelete(table);
                String code = methodProcedureDelete.getCode();
                sb.append(code);
            }

            if (MethodMerge) {
                MethodProcedureMerge methodProcedureMerge = new MethodProcedureMerge(table);
                String code = methodProcedureMerge.getCode();
                sb.append(code);
            }

            if (MethodSelect) {
                MethodProcedureSelect methodProcedureSelect = new MethodProcedureSelect(table);
                String code = methodProcedureSelect.getCode();
                sb.append(code);
            }

            if (MethodSelectPrint) {
                MethodProcedureSelectPrint methodProcedureSelectPrint = new MethodProcedureSelectPrint(table);
                String code = methodProcedureSelectPrint.getCode();
                sb.append(code);
            }

            if (MethodDeleteAll) {
                MethodProcedureDeleteAll methodProcedureDeleteAll = new MethodProcedureDeleteAll(table);
                String code = methodProcedureDeleteAll.getCode();
                sb.append(code);
            }

            if (MethodSelectAll) {
                MethodProcedureSelectAll methodProcedureSelectAll = new MethodProcedureSelectAll(table);
                String code = methodProcedureSelectAll.getCode();
                sb.append(code);
            }

            if (MethodSelectAllPrint) {
                MethodProcedureSelectAllPrint methodProcedureSelectAllPrint = new MethodProcedureSelectAllPrint(table);
                String code = methodProcedureSelectAllPrint.getCode();
                sb.append(code);
            }

            if (MethodFunctionCount) {
                MethodFunctionCount methodFunctionCount = new MethodFunctionCount(table);
                String code = methodFunctionCount.getCode();
                sb.append(code);
            }

            if (MethodFunctionCountPrint) {
                MethodFunctionCountPrint methodFunctionCountPrint = new MethodFunctionCountPrint(table);
                String code = methodFunctionCountPrint.getCode();
                sb.append(code);
            }

            if (DataObject) {
                DataObject dataObject = new DataObject(table, dataObjectStuff);
                sb.append(dataObject.getCode());
            }

        }

        if (TestClass) {
            sb.append(createTestClass.getCode());
        }

        return sb.toString();
    }

    public String getMysql() {

        StringBuilder sb = new StringBuilder();
        sb.append("Not implemented yet.");
        return sb.toString();
    }

    public void setMysql(boolean mysql) {

        isMysql = mysql;
    }

    public void setHsqldb(boolean hsqldb) {

        isHsqldb = hsqldb;
    }

    public void setTablesDup(boolean tablesdup) {
        TablesDup = tablesdup;
    }

    public void setMethodSelectAllPrint(boolean selected) {
        MethodSelectAllPrint = selected;
    }

    public void setMethodSelectPrint(boolean selected) {
        MethodSelectPrint = selected;
    }

    public void setMethodFunctionCountPrint(boolean selected) {
        MethodFunctionCountPrint = selected;
    }

    public void setStmtInsertSelect(boolean stmtInsertSelect) {
        this.StmtInsertSelect = stmtInsertSelect;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getSchemaName() {
        return schemaName;
    }
}
