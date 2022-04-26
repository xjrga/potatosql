package io.github.xjrga.potatosql.generator;

import io.github.xjrga.potatosql.data.Dblink;
import io.github.xjrga.potatosql.data.dto.O_schema;
import io.github.xjrga.potatosql.data.dto.O_table;
import io.github.xjrga.potatosql.generator.hsqldb.CreateRelationship;
import io.github.xjrga.potatosql.generator.hsqldb.CreateSchema;
import io.github.xjrga.potatosql.generator.hsqldb.CreateTable;
import io.github.xjrga.potatosql.generator.hsqldb.CreateTableUsingSelect;
import io.github.xjrga.potatosql.generator.hsqldb.CreateView;
import io.github.xjrga.potatosql.generator.hsqldb.DropSchema;
import io.github.xjrga.potatosql.generator.hsqldb.FunctionCount;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureDelete;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureDeleteAll;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureInsert;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureMerge;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureSelect;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureSelectAll;
import io.github.xjrga.potatosql.generator.hsqldb.ProcedureUpdate;
import io.github.xjrga.potatosql.generator.hsqldb.SetSchema;
import io.github.xjrga.potatosql.generator.hsqldb.SqlStuff;
import io.github.xjrga.potatosql.generator.hsqldb.StatementDelete;
import io.github.xjrga.potatosql.generator.hsqldb.StatementDeleteAll;
import io.github.xjrga.potatosql.generator.hsqldb.StatementInsert;
import io.github.xjrga.potatosql.generator.hsqldb.StatementInsertUsingSelect;
import io.github.xjrga.potatosql.generator.hsqldb.StatementMerge;
import io.github.xjrga.potatosql.generator.hsqldb.StatementSelect;
import io.github.xjrga.potatosql.generator.hsqldb.StatementSelectAll;
import io.github.xjrga.potatosql.generator.hsqldb.StatementSelectCount;
import io.github.xjrga.potatosql.generator.hsqldb.StatementUpdate;
import io.github.xjrga.potatosql.generator.hsqldb.TriggerRowAfterDelete;
import io.github.xjrga.potatosql.generator.hsqldb.TriggerRowAfterInsert;
import io.github.xjrga.potatosql.generator.hsqldb.TriggerRowAfterUpdate;
import io.github.xjrga.potatosql.generator.hsqldb.TriggerRowBeforeDelete;
import io.github.xjrga.potatosql.generator.hsqldb.TriggerRowBeforeInsert;
import io.github.xjrga.potatosql.generator.hsqldb.TriggerRowBeforeUpdate;
import io.github.xjrga.potatosql.generator.hsqldb.TriggerStatementAfterDelete;
import io.github.xjrga.potatosql.generator.hsqldb.TriggerStatementAfterInsert;
import io.github.xjrga.potatosql.generator.hsqldb.TriggerStatementAfterUpdate;
import io.github.xjrga.potatosql.generator.hsqldb.TriggerStuff;
import io.github.xjrga.potatosql.generator.java.CreateTestClass;
import io.github.xjrga.potatosql.generator.java.Data_transfer_object;
import io.github.xjrga.potatosql.generator.java.Data_transfer_object_stuff;
import io.github.xjrga.potatosql.generator.java.Generic_dao;
import io.github.xjrga.potatosql.generator.java.MethodFunctionCount;
import io.github.xjrga.potatosql.generator.java.MethodFunctionCountPrint;
import io.github.xjrga.potatosql.generator.java.MethodProcedureDelete;
import io.github.xjrga.potatosql.generator.java.MethodProcedureDeleteAll;
import io.github.xjrga.potatosql.generator.java.MethodProcedureInsert;
import io.github.xjrga.potatosql.generator.java.MethodProcedureMerge;
import io.github.xjrga.potatosql.generator.java.MethodProcedureSelect;
import io.github.xjrga.potatosql.generator.java.MethodProcedureSelectAll;
import io.github.xjrga.potatosql.generator.java.MethodProcedureSelectAllPrint;
import io.github.xjrga.potatosql.generator.java.MethodProcedureSelectPrint;
import io.github.xjrga.potatosql.generator.java.MethodProcedureUpdate;
import io.github.xjrga.potatosql.generator.java.Typed_dao_interface;
import java.util.List;

public class DialectBuilder implements Code {

    private final Dblink dblink;
    private boolean generic_data_access_object;
    private boolean data_access_object;
    private boolean data_transfer_object;
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
    private int schema_id;
    private boolean is_hsqldb_selected;
    private boolean is_mysql_selected;
    private CreateTestClass createTestClass;
    private String schema_name;
    private O_schema schema;
    private List<O_table> table_list;

    public DialectBuilder(Dblink dbLink) {
        this.dblink = dbLink;
    }

    @Override
    public String get_code() {
        StringBuilder sb = new StringBuilder();

        if (is_hsqldb_selected) {
            sb.append(get_hsqldb());

        } else if (is_mysql_selected) {
            sb.append(get_mysql());
        }
        return sb.toString();
    }

    public void set_oschema(O_schema schema) {
        this.schema = schema;
        this.schema_id = schema.getSchema_id();
        this.schema_name = schema.getSchema_name();
    }

    public void is_triggerstatementafterupdate_selected(boolean triggerStatementAfterUpdate) {
        TriggerStatementAfterUpdate = triggerStatementAfterUpdate;
    }

    public void is_triggerstatementafterdelete_selected(boolean triggerStatementAfterDelete) {
        TriggerStatementAfterDelete = triggerStatementAfterDelete;
    }

    public void is_triggerrowbeforeinsert_selected(boolean triggerRowBeforeInsert) {
        TriggerRowBeforeInsert = triggerRowBeforeInsert;
    }

    public void is_triggerrowbeforeupdate_selected(boolean triggerRowBeforeUpdate) {
        TriggerRowBeforeUpdate = triggerRowBeforeUpdate;
    }

    public void is_triggerrowbeforedelete_selected(boolean triggerRowBeforeDelete) {
        TriggerRowBeforeDelete = triggerRowBeforeDelete;
    }

    public void is_triggerrowafterinsert_selected(boolean triggerRowAfterInsert) {
        TriggerRowAfterInsert = triggerRowAfterInsert;
    }

    public void is_triggerrowafterupdate_selected(boolean triggerRowAfterUpdate) {
        TriggerRowAfterUpdate = triggerRowAfterUpdate;
    }

    public void is_triggerrowafterdelete_selected(boolean triggerRowAfterDelete) {
        TriggerRowAfterDelete = triggerRowAfterDelete;
    }

    public void is_views_selected(boolean views) {
        Views = views;
    }

    public void is_testclass_selected(boolean testClass) {
        TestClass = testClass;
    }

    public void is_tables_selected(boolean tables) {
        Tables = tables;
    }

    public void is_stmtinsert_selected(boolean stmtInsert) {
        StmtInsert = stmtInsert;
    }

    public void is_stmtupdate_selected(boolean stmtUpdate) {
        StmtUpdate = stmtUpdate;
    }

    public void is_stmtdelete_selected(boolean stmtDelete) {
        StmtDelete = stmtDelete;
    }

    public void is_stmtmerge_selected(boolean stmtMerge) {
        StmtMerge = stmtMerge;
    }

    public void is_stmtselect_selected(boolean stmtSelect) {
        StmtSelect = stmtSelect;
    }

    public void is_stmtdeleteall_selected(boolean stmtDeleteAll) {
        StmtDeleteAll = stmtDeleteAll;
    }

    public void is_stmtselectall_selected(boolean stmtSelectAll) {
        StmtSelectAll = stmtSelectAll;
    }

    public void is_stmtcount_selected(boolean stmtCount) {
        StmtCount = stmtCount;
    }

    public void is_stmtcreateselect_selected(boolean stmtCreateSelect) {
        StmtCreateSelect = stmtCreateSelect;
    }

    public void is_procinsert_selected(boolean procInsert) {
        ProcInsert = procInsert;
    }

    public void is_procupdate_selected(boolean procUpdate) {
        ProcUpdate = procUpdate;
    }

    public void is_procdelete_selected(boolean procDelete) {
        ProcDelete = procDelete;
    }

    public void is_procmerge_selected(boolean procMerge) {
        ProcMerge = procMerge;
    }

    public void is_procselect_selected(boolean procSelect) {
        ProcSelect = procSelect;
    }

    public void is_procdeleteall_selected(boolean procDeleteAll) {
        ProcDeleteAll = procDeleteAll;
    }

    public void is_procselectall_selected(boolean procSelectAll) {
        ProcSelectAll = procSelectAll;
    }

    public void is_setfunctioncount_selected(boolean functionCount) {
        FunctionCount = functionCount;
    }

    public void is_methodinsert_selected(boolean methodInsert) {
        MethodInsert = methodInsert;
    }

    public void is_methodupdate_selected(boolean methodUpdate) {
        MethodUpdate = methodUpdate;
    }

    public void is_methoddelete_selected(boolean methodDelete) {
        MethodDelete = methodDelete;
    }

    public void is_methodmerge_selected(boolean methodMerge) {
        MethodMerge = methodMerge;
    }

    public void is_methodselect_selected(boolean methodSelect) {
        MethodSelect = methodSelect;
    }

    public void is_methoddeleteall_selected(boolean methodDeleteAll) {
        MethodDeleteAll = methodDeleteAll;
    }

    public void is_methodselectall_selected(boolean methodSelectAll) {
        MethodSelectAll = methodSelectAll;
    }

    public void is_methodfunctioncount_selected(boolean methodFunctionCount) {
        MethodFunctionCount = methodFunctionCount;
    }

    public void is_triggerstatementafterinsert_selected(boolean triggerStatementAfterInsert) {
        TriggerStatementAfterInsert = triggerStatementAfterInsert;
    }

    public void is_data_transfer_object_selected(boolean dataObject) {
        data_transfer_object = dataObject;
    }

    public void is_generic_data_access_object_selected(boolean generic_data_access_object) {
        this.generic_data_access_object = generic_data_access_object;
    }

    public void is_data_access_object_selected(boolean data_access_object) {
        this.data_access_object = data_access_object;
    }

    public void is_mysql_selected(boolean mysql) {

        is_mysql_selected = mysql;
    }

    public void is_hsqldb_selected(boolean hsqldb) {

        is_hsqldb_selected = hsqldb;
    }

    public void is_methodselectallprint_selected(boolean selected) {
        MethodSelectAllPrint = selected;
    }

    public void is_methodselectprint_selected(boolean selected) {
        MethodSelectPrint = selected;
    }

    public void is_methodfunctioncountprint_selected(boolean selected) {
        MethodFunctionCountPrint = selected;
    }

    public void is_stmtinsertselect_selected(boolean stmtInsertSelect) {
        this.StmtInsertSelect = stmtInsertSelect;
    }

    public void setSchema_name(String schema_name) {
        this.schema_name = schema_name;
    }

    public String getSchema_name() {
        return schema_name;
    }

    public void set_otables(List<O_table> list) {
        this.table_list = list;
    }

    public String get_hsqldb() {
        StringBuilder sb = new StringBuilder();

        TableMaker tableMaker = new TableMaker(dblink);

        if (TestClass) {
            createTestClass = new CreateTestClass();
        }

        if (Tables) {
            DropSchema dropSchema = new DropSchema(this.getSchema_name());
            sb.append(dropSchema.get_code());
            sb.setLength(sb.length() - 1);
            sb.append("/\n\n");
            CreateSchema createSchema = new CreateSchema(this.getSchema_name());
            sb.append(createSchema.get_code());
            sb.setLength(sb.length() - 1);
            sb.append("/\n\n");
            SetSchema setSchema = new SetSchema(this.getSchema_name());
            sb.append(setSchema.get_code());
            sb.setLength(sb.length() - 1);
            sb.append("/\n\n");
        }

        for (int i = 0; i < table_list.size(); i++) {
            O_table otable = table_list.get(i);
            Integer tableId = otable.getTable_id();
            Table table = tableMaker.get_table(schema, tableId);
            SqlStuff sqlStuff = new SqlStuff(table);

            if (Tables) {
                CreateTable createTable = new CreateTable(table, sqlStuff);
                sb.append(createTable.get_code());
                sb.setLength(sb.length() - 1);
                sb.append("/\n\n");
            }
        }

        if (Tables) {
            CreateRelationship createRelationship = new CreateRelationship(dblink, schema_id);
            sb.append(createRelationship.get_code());

        }

        if (Views | StmtCreateSelect) {
            SetSchema setSchema = new SetSchema(this.getSchema_name());
            sb.append(setSchema.get_code());
            sb.setLength(sb.length() - 1);
            sb.append("/\n\n");
        }

        for (int i = 0; i < table_list.size(); i++) {
            O_table otable = table_list.get(i);
            Integer tableId = otable.getTable_id();
            Table table = tableMaker.get_table(schema, tableId);
            SqlStuff sqlStuff = new SqlStuff(table);

            if (Views) {
                CreateView createView = new CreateView(table, sqlStuff);
                sb.append(createView.get_code());
                sb.setLength(sb.length() - 1);
                sb.append("/\n\n");
            }

            if (StmtCreateSelect) {
                CreateTableUsingSelect createTableUsingSelect = new CreateTableUsingSelect(table, sqlStuff);
                sb.append(createTableUsingSelect.get_code());
                sb.setLength(sb.length() - 1);
                sb.append("/\n\n");
            }

        }

        if (ProcInsert | ProcUpdate | ProcDelete | ProcMerge | ProcSelect | ProcDeleteAll | ProcSelectAll | FunctionCount | TriggerRowBeforeInsert | TriggerRowBeforeUpdate | TriggerRowBeforeDelete | TriggerRowAfterInsert | TriggerRowAfterUpdate | TriggerRowAfterDelete | TriggerStatementAfterInsert | TriggerStatementAfterUpdate | TriggerStatementAfterDelete) {
            SetSchema setSchema = new SetSchema(this.getSchema_name());
            sb.append(setSchema.get_code());
            sb.setLength(sb.length() - 1);
            sb.append("/\n\n");
        }

        for (int i = 0; i < table_list.size(); i++) {
            O_table otable = table_list.get(i);
            Integer tableId = otable.getTable_id();
            Table table = tableMaker.get_table(schema, tableId);
            SqlStuff sqlStuff = new SqlStuff(table);
            TriggerStuff triggerStuff = new TriggerStuff(table);

            if (ProcInsert) {
                ProcedureInsert procedureInsert = new ProcedureInsert(table, sqlStuff);
                sb.append(procedureInsert.get_code());
            }

            if (ProcUpdate) {
                ProcedureUpdate procedureUpdate = new ProcedureUpdate(table, sqlStuff);
                sb.append(procedureUpdate.get_code());
            }

            if (ProcDelete) {
                ProcedureDelete procedureDelete = new ProcedureDelete(table, sqlStuff);
                sb.append(procedureDelete.get_code());
            }

            if (ProcMerge) {
                ProcedureMerge procedureMerge = new ProcedureMerge(table, sqlStuff);
                sb.append(procedureMerge.get_code());
            }

            if (ProcSelect) {
                ProcedureSelect procedureSelect = new ProcedureSelect(table, sqlStuff);
                sb.append(procedureSelect.get_code());
            }

            if (ProcDeleteAll) {
                ProcedureDeleteAll procedureDeleteAll = new ProcedureDeleteAll(table);
                sb.append(procedureDeleteAll.get_code());
            }

            if (ProcSelectAll) {
                ProcedureSelectAll procedureSelectAll = new ProcedureSelectAll(table, sqlStuff);
                sb.append(procedureSelectAll.get_code());
            }

            if (FunctionCount) {
                FunctionCount functionCount = new FunctionCount(table);
                sb.append(functionCount.get_code());
            }

            if (TriggerRowBeforeInsert) {
                TriggerRowBeforeInsert triggerRowBeforeInsert = new TriggerRowBeforeInsert(table);
                sb.append(triggerRowBeforeInsert.get_code());
            }

            if (TriggerRowBeforeUpdate) {
                TriggerRowBeforeUpdate triggerRowBeforeUpdate = new TriggerRowBeforeUpdate(table, triggerStuff);
                sb.append(triggerRowBeforeUpdate.get_code());
            }

            if (TriggerRowBeforeDelete) {
                TriggerRowBeforeDelete triggerRowBeforeDelete = new TriggerRowBeforeDelete(table);
                sb.append(triggerRowBeforeDelete.get_code());
            }

            if (TriggerRowAfterInsert) {
                TriggerRowAfterInsert triggerRowAfterInsert = new TriggerRowAfterInsert(table, triggerStuff);
                sb.append(triggerRowAfterInsert.get_code());
            }

            if (TriggerRowAfterUpdate) {
                TriggerRowAfterUpdate triggerRowAfterUpdate = new TriggerRowAfterUpdate(table, triggerStuff);
                sb.append(triggerRowAfterUpdate.get_code());
            }

            if (TriggerRowAfterDelete) {
                TriggerRowAfterDelete triggerRowAfterDelete = new TriggerRowAfterDelete(table, triggerStuff);
                sb.append(triggerRowAfterDelete.get_code());
            }

            if (TriggerStatementAfterInsert) {
                TriggerStatementAfterInsert triggerStatementAfterInsert = new TriggerStatementAfterInsert(table);
                sb.append(triggerStatementAfterInsert.get_code());
            }

            if (TriggerStatementAfterUpdate) {
                TriggerStatementAfterUpdate triggerStatementAfterUpdate = new TriggerStatementAfterUpdate(table);
                sb.append(triggerStatementAfterUpdate.get_code());
            }

            if (TriggerStatementAfterDelete) {
                TriggerStatementAfterDelete triggerStatementAfterDelete = new TriggerStatementAfterDelete(table);
                sb.append(triggerStatementAfterDelete.get_code());
            }

        }

        if (TestClass) {
            sb.append(createTestClass.get_code());
        }

        for (int i = 0; i < table_list.size(); i++) {
            O_table otable = table_list.get(i);
            Integer tableId = otable.getTable_id();
            Table table = tableMaker.get_table(schema, tableId);
            SqlStuff sqlStuff = new SqlStuff(table);
            Data_transfer_object_stuff dataObjectStuff = new Data_transfer_object_stuff(table);

            if (StmtInsert) {
                StatementInsert statementInsert = new StatementInsert(table, sqlStuff);
                sb.append(statementInsert.get_code());
            }

            if (StmtUpdate) {
                StatementUpdate statementUpdate = new StatementUpdate(table, sqlStuff);
                sb.append(statementUpdate.get_code());
            }

            if (StmtDelete) {
                StatementDelete statementDelete = new StatementDelete(table, sqlStuff);
                sb.append(statementDelete.get_code());
            }

            if (StmtMerge) {
                StatementMerge statementMerge = new StatementMerge(table, sqlStuff);
                sb.append(statementMerge.get_code());
            }

            if (StmtSelect) {
                StatementSelect statementSelect = new StatementSelect(table, sqlStuff);
                sb.append(statementSelect.get_code());
            }

            if (StmtDeleteAll) {
                StatementDeleteAll statementDeleteAll = new StatementDeleteAll(table);
                sb.append(statementDeleteAll.get_code());
            }

            if (StmtSelectAll) {
                StatementSelectAll statementSelectAll = new StatementSelectAll(table, sqlStuff);
                sb.append(statementSelectAll.get_code());
            }

            if (StmtCount) {
                StatementSelectCount statementSelectCount = new StatementSelectCount(table);
                sb.append(statementSelectCount.get_code());
            }

            if (StmtInsertSelect) {
                StatementInsertUsingSelect statementInsertUsingSelect = new StatementInsertUsingSelect(table, sqlStuff);
                sb.append(statementInsertUsingSelect.get_code());
                sb.setLength(sb.length() - 1);
                sb.append("/\n\n");
            }

            if (MethodInsert) {
                MethodProcedureInsert methodProcedureInsert = new MethodProcedureInsert(table);
                String code = methodProcedureInsert.get_code();
                sb.append(code);
            }

            if (MethodUpdate) {
                MethodProcedureUpdate methodProcedureUpdate = new MethodProcedureUpdate(table);
                String code = methodProcedureUpdate.get_code();
                sb.append(code);
            }

            if (MethodDelete) {
                MethodProcedureDelete methodProcedureDelete = new MethodProcedureDelete(table);
                String code = methodProcedureDelete.get_code();
                sb.append(code);
            }

            if (MethodMerge) {
                MethodProcedureMerge methodProcedureMerge = new MethodProcedureMerge(table);
                String code = methodProcedureMerge.get_code();
                sb.append(code);
            }

            if (MethodSelect) {
                MethodProcedureSelect methodProcedureSelect = new MethodProcedureSelect(table);
                String code = methodProcedureSelect.get_code();
                sb.append(code);
            }

            if (MethodSelectPrint) {
                MethodProcedureSelectPrint methodProcedureSelectPrint = new MethodProcedureSelectPrint(table);
                String code = methodProcedureSelectPrint.get_code();
                sb.append(code);
            }

            if (MethodDeleteAll) {
                MethodProcedureDeleteAll methodProcedureDeleteAll = new MethodProcedureDeleteAll(table);
                String code = methodProcedureDeleteAll.get_code();
                sb.append(code);
            }

            if (MethodSelectAll) {
                MethodProcedureSelectAll methodProcedureSelectAll = new MethodProcedureSelectAll(table);
                String code = methodProcedureSelectAll.get_code();
                sb.append(code);
            }

            if (MethodSelectAllPrint) {
                MethodProcedureSelectAllPrint methodProcedureSelectAllPrint = new MethodProcedureSelectAllPrint(table);
                String code = methodProcedureSelectAllPrint.get_code();
                sb.append(code);
            }

            if (MethodFunctionCount) {
                MethodFunctionCount methodFunctionCount = new MethodFunctionCount(table);
                String code = methodFunctionCount.get_code();
                sb.append(code);
            }

            if (MethodFunctionCountPrint) {
                MethodFunctionCountPrint methodFunctionCountPrint = new MethodFunctionCountPrint(table);
                String code = methodFunctionCountPrint.get_code();
                sb.append(code);
            }

            if (data_transfer_object) {
                Data_transfer_object dataObject = new Data_transfer_object(table, dataObjectStuff);
                sb.append(dataObject.get_code());
            }

            if (data_access_object) {
                Typed_dao_interface dao = new Typed_dao_interface(table);
                sb.append(dao.get_code());
            }

        }

        if (generic_data_access_object) {
            Generic_dao dao = new Generic_dao();
            sb.append(dao.get_code());
        }

        return sb.toString();
    }

    public String get_mysql() {

        StringBuilder sb = new StringBuilder();
        sb.append("This functionality has not been implemented yet. You are invited to improve it. All suggestions and comments are welcome.");
        return sb.toString();
    }

}
