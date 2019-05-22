package org.xjrga.potatosql.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Import
{
    public Import(){

    }

    public static void main(String[] args)
    {

    }

    private void readFile(File file)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str = "";
            StringBuilder textArea = new StringBuilder();
            int linenumber = 1;

            while((str = in.readLine())!= null){

                if(!equalFields(4,str)){
                    textArea.append("Number of fields is not equal on line ");
                    textArea.append(String.valueOf(linenumber));
                    textArea.append(" -> ");
                }

                //PrintProcedureInsertCall printProcedureInsertCall = new PrintProcedureInsertCall(table);
                //printProcedureInsertCall.setStr(process(str));
                //textArea.append(printProcedureInsertCall.getCode());

                linenumber++;

            }

            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private String process(String str){

        String[] fields = str.split("\\;");
        int size = fields.length;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++)
        {
            String field = fields[i];

            if(!field.isEmpty()){
                field = field.replace('"','\'');
                sb.append(field);
            }else{
                sb.append("''");
            }
            sb.append(",");

        }

        if(sb.length()>0){
            sb.setLength(sb.length()-1);
        }

        return sb.toString();

    }


    private boolean equalFields(int keyCount, String str){

        boolean flag = false;
        String[] fields = str.split(";");
        int size = fields.length;

        if(size==keyCount){
            flag = true;
        }else{

            System.out.println(keyCount+"!="+size+" -> ");

            for (int i = 0; i < fields.length; i++)
            {
                String field = fields[i];
                System.out.println(i+":"+field);
            }
        }

        return flag;
    }


}
