package io.github.xjrga.potatosql.other;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Log {

    private static final Log INSTANCE = new Log();
    private BufferedWriter out;
    private LinkedList<String> messageList;

    private Log() {
    }

    public static Log getLog() {
        return INSTANCE;
    }

    public void logMessage(String message) {
        messageList.add(message);
    }

    public void removeLast() {
        messageList.removeLast();
    }

    public void remove(int index) {
        messageList.remove(index);
    }

    public void removeFirst() {
        messageList.removeFirst();
    }

    public void clear() {
        messageList.clear();
    }

    public void start(String filePath) {
        messageList = new LinkedList<>();
        try {
            out = new BufferedWriter(new FileWriter(filePath, true));
        } catch (IOException e) {
            Log.getLog().start("files/exception.log");
            Log.getLog().logMessage(e.toString());
            Log.getLog().write();
            Log.getLog().close();
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            out.close();
        } catch (IOException e) {
            Log.getLog().start("files/exception.log");
            Log.getLog().logMessage(e.toString());
            Log.getLog().write();
            Log.getLog().close();
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            out.write("----->");
            out.write("\n");
            messageList.forEach(m -> {
                try {
                    out.write(m);
                    out.write("\n");
                } catch (IOException e) {
                    Log.getLog().start("files/exception.log");
                    Log.getLog().logMessage(e.toString());
                    Log.getLog().write();
                    Log.getLog().close();
                    e.printStackTrace();
                }
            });
            out.write("<-----");
            out.write("\n\n");
        } catch (IOException e) {
            Log.getLog().start("files/exception.log");
            Log.getLog().logMessage(e.toString());
            Log.getLog().write();
            Log.getLog().close();
            e.printStackTrace();
        }
    }
}
