package org.xjrga.potatosql.other;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Write
{

    public Write()
    {

    }


    public static void writeToFile(String txt)
    {

        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter("script.sql"));
            out.write(txt);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
