package org.xjrga.potatosql.other;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Write {

    public Write() {

    }


    public static void writeToFile(String txt, String filename) {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("scripts/" + filename));
            out.write(txt);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
