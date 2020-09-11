package org.xjrga.potatosql.generator;

import org.xjrga.potatosql.data.DbLink;
import org.xjrga.potatosql.dataobject.DatabaseSchemaDataObject;
import org.xjrga.potatosql.dataobject.DatabaseTableDataObject;

public class Generator2 implements Code {
    private final DbLink dbLink;
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
    private boolean isHsqldb;
    private boolean isMysql;
    private boolean TablesDup;
    private DatabaseSchemaDataObject databaseSchemaDataObject;
    private DatabaseTableDataObject databaseTableDataObject;

    public Generator2(DbLink dbLink) {
        this.dbLink = dbLink;
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
        Table table = tableMaker.getTable(getDatabaseSchemaDataObject().getSchemaId(), getDatabaseTableDataObject().getTableId());
        table.setSchema(getDatabaseSchemaDataObject().getName());
        SqlStuff sqlStuff = new SqlStuff(table);
        TriggerStuff triggerStuff = new TriggerStuff(table);
        DataObjectStuff dataObjectStuff = new DataObjectStuff(table);
//statement create
        /*if (Tables)
        {
            CreateTable createTable = new CreateTable(table, sqlStuff);
            sb.append(createTable.getCode());
            sb.append("\n");
            sb.append("\n");
        }*/
        if (TablesDup) {
            CreateTableDup createTableDup = new CreateTableDup(table, sqlStuff);
            sb.append(createTableDup.getCode());
            sb.append("\n");
            sb.append("\n");
        }
//statement insert
        if (StmtInsert) {
            StatementInsert statementInsert = new StatementInsert(table, sqlStuff);
            sb.append(statementInsert.getCode());
            sb.append("\n");
            sb.append("\n");
        }
//statement update
        if (StmtUpdate) {
            StatementUpdate statementUpdate = new StatementUpdate(table, sqlStuff);
            sb.append(statementUpdate.getCode());
            sb.append("\n");
            sb.append("\n");
        }
//statement delete
        if (StmtDelete) {
            StatementDelete statementDelete = new StatementDelete(table, sqlStuff);
            sb.append(statementDelete.getCode());
            sb.append("\n");
            sb.append("\n");
        }
//statement merge
        if (StmtMerge) {
            StatementMerge statementMerge = new StatementMerge(table, sqlStuff);
            sb.append(statementMerge.getCode());
            sb.append("\n");
            sb.append("\n");
        }
//statement select
        if (StmtSelect) {
            StatementSelect statementSelect = new StatementSelect(table, sqlStuff);
            sb.append(statementSelect.getCode());
            sb.append("\n");
            sb.append("\n");
        }
//statement deleteall
        if (StmtDeleteAll) {
            StatementDeleteAll statementDeleteAll = new StatementDeleteAll(table, sqlStuff);
            sb.append(statementDeleteAll.getCode());
            sb.append("\n");
            sb.append("\n");
        }
//statement select all
        if (StmtSelectAll) {
            StatementSelectAll statementSelectAll = new StatementSelectAll(table, sqlStuff);
            sb.append(statementSelectAll.getCode());
            sb.append("\n");
            sb.append("\n");
        }
//statement select count
        if (StmtCount) {
            StatementSelectCount statementSelectCount = new StatementSelectCount(table);
            sb.append(statementSelectCount.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //statement create select
        if (StmtCreateSelect) {
            CreateTableUsingSelect createTableUsingSelect = new CreateTableUsingSelect(table, sqlStuff);
            sb.append(createTableUsingSelect.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //statement insert select
        if (StmtInsertSelect) {
            StatementInsertUsingSelect statementInsertUsingSelect = new StatementInsertUsingSelect(table, sqlStuff);
            sb.append(statementInsertUsingSelect.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //procinsert
        if (ProcInsert) {
            ProcedureInsert procedureInsert = new ProcedureInsert(table, sqlStuff);
            sb.append(procedureInsert.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //procupdate
        if (ProcUpdate) {
            ProcedureUpdate procedureUpdate = new ProcedureUpdate(table, sqlStuff);
            sb.append(procedureUpdate.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //procdelete
        if (ProcDelete) {
            ProcedureDelete procedureDelete = new ProcedureDelete(table, sqlStuff);
            sb.append(procedureDelete.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //procmerge
        if (ProcMerge) {
            ProcedureMerge procedureMerge = new ProcedureMerge(table, sqlStuff);
            sb.append(procedureMerge.getCode());
            sb.append("\n");
            sb.append("\n");
        }
//procedure select
        if (ProcSelect) {
            ProcedureSelect procedureSelect = new ProcedureSelect(table, sqlStuff);
            sb.append(procedureSelect.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //procdeleteall
        if (ProcDeleteAll) {
            ProcedureDeleteAll procedureDeleteAll = new ProcedureDeleteAll(table, sqlStuff);
            sb.append(procedureDeleteAll.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //procselectall
        if (ProcSelectAll) {
            ProcedureSelectAll procedureSelectAll = new ProcedureSelectAll(table, sqlStuff);
            sb.append(procedureSelectAll.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //functioncount
        if (FunctionCount) {
            FunctionCount functionCount = new FunctionCount(table);
            sb.append(functionCount.getCode());
            sb.append("\n");
            sb.append("\n");
        }
//method insert
        if (MethodInsert) {
            MethodProcedureInsert methodProcedureInsert = new MethodProcedureInsert(table);
            String code = methodProcedureInsert.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");
        }
        //method update
        if (MethodUpdate) {
            MethodProcedureUpdate methodProcedureUpdate = new MethodProcedureUpdate(table);
            String code = methodProcedureUpdate.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");
        }
        //method delete
        if (MethodDelete) {
            MethodProcedureDelete methodProcedureDelete = new MethodProcedureDelete(table);
            String code = methodProcedureDelete.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");
        }
        //method merge
        if (MethodMerge) {
            MethodProcedureMerge methodProcedureMerge = new MethodProcedureMerge(table);
            String code = methodProcedureMerge.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");
        }
        //method select
        if (MethodSelect) {
            MethodProcedureSelect methodProcedureSelect = new MethodProcedureSelect(table);
            String code = methodProcedureSelect.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");
        }
        //method select print
        if (MethodSelectPrint) {
            MethodProcedureSelectPrint methodProcedureSelectPrint = new MethodProcedureSelectPrint(table);
            String code = methodProcedureSelectPrint.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");
        }
        //method delete all
        if (MethodDeleteAll) {
            MethodProcedureDeleteAll methodProcedureDeleteAll = new MethodProcedureDeleteAll(table);
            String code = methodProcedureDeleteAll.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");
        }
        //method select all
        if (MethodSelectAll) {
            MethodProcedureSelectAll methodProcedureSelectAll = new MethodProcedureSelectAll(table);
            String code = methodProcedureSelectAll.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");
        }
        //method select all print
        if (MethodSelectAllPrint) {
            MethodProcedureSelectAllPrint methodProcedureSelectAllPrint = new MethodProcedureSelectAllPrint(table);
            String code = methodProcedureSelectAllPrint.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");
        }
        //method function count
        if (MethodFunctionCount) {
            MethodFunctionCount methodFunctionCount = new MethodFunctionCount(table);
            String code = methodFunctionCount.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");
        }
        //views
        if (Views) {
            CreateView createView = new CreateView(table, sqlStuff);
            sb.append(createView.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //trigger row before insert
        if (TriggerRowBeforeInsert) {
            TriggerRowBeforeInsert triggerRowBeforeInsert = new TriggerRowBeforeInsert(table, triggerStuff);
            sb.append(triggerRowBeforeInsert.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //trigger row before update
        if (TriggerRowBeforeUpdate) {
            TriggerRowBeforeUpdate triggerRowBeforeUpdate = new TriggerRowBeforeUpdate(table, triggerStuff);
            sb.append(triggerRowBeforeUpdate.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //trigger row before delete
        if (TriggerRowBeforeDelete) {
            TriggerRowBeforeDelete triggerRowBeforeDelete = new TriggerRowBeforeDelete(table, triggerStuff);
            sb.append(triggerRowBeforeDelete.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //trigger row after insert
        if (TriggerRowAfterInsert) {
            TriggerRowAfterInsert triggerRowAfterInsert = new TriggerRowAfterInsert(table, triggerStuff);
            sb.append(triggerRowAfterInsert.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //trigger row after update
        if (TriggerRowAfterUpdate) {
            TriggerRowAfterUpdate triggerRowAfterUpdate = new TriggerRowAfterUpdate(table, triggerStuff);
            sb.append(triggerRowAfterUpdate.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //trigger row after delete
        if (TriggerRowAfterDelete) {
            TriggerRowAfterDelete triggerRowAfterDelete = new TriggerRowAfterDelete(table, triggerStuff);
            sb.append(triggerRowAfterDelete.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //trigger statement after insert
        if (TriggerStatementAfterInsert) {
            TriggerStatementAfterInsert triggerStatementAfterInsert = new TriggerStatementAfterInsert(table, triggerStuff);
            sb.append(triggerStatementAfterInsert.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //trigger statement after update
        if (TriggerStatementAfterUpdate) {
            TriggerStatementAfterUpdate triggerStatementAfterUpdate = new TriggerStatementAfterUpdate(table, triggerStuff);
            sb.append(triggerStatementAfterUpdate.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        //trigger statement after delete
        if (TriggerStatementAfterDelete) {
            TriggerStatementAfterDelete triggerStatementAfterDelete = new TriggerStatementAfterDelete(table, triggerStuff);
            sb.append(triggerStatementAfterDelete.getCode());
            sb.append("\n");
            sb.append("\n");
        }
        if (DataObject) {
            DataObject dataObject = new DataObject(table, dataObjectStuff);
            sb.append(dataObject.getCode());
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

    public DatabaseTableDataObject getDatabaseTableDataObject() {
        return databaseTableDataObject;
    }

    public void setDatabaseTableDataObject(DatabaseTableDataObject databaseTableDataObject) {
        this.databaseTableDataObject = databaseTableDataObject;
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

    public void setStmtInsertSelect(boolean stmtInsertSelect) {
        this.StmtInsertSelect = stmtInsertSelect;
    }
}
