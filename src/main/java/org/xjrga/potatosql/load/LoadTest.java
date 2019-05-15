package org.xjrga.potatosql.load;

public class LoadTest
{
    public LoadTest()
    {

        LoadDatabase loadDatabase = new LoadDatabase();

        try
        {
            loadDatabase.turnOffAutocommit();
            //insert,update,delete
            loadDatabase.turnOnAutocommit();
        }
        catch (Exception e)
        {
            loadDatabase.rollback();
        }
        finally
        {
            loadDatabase.shutdown();
        }
    }


    public static void main(String[] args)
    {
        LoadTest loadTest = new LoadTest();
    }
}
