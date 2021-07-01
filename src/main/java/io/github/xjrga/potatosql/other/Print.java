package io.github.xjrga.potatosql.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Print {

    public static void main(String[] args) {
        Print test = new Print();
        Object[] parameters = new Object[2];
        parameters[0] = 100;
        parameters[1] = "X";
        test.printCall("?Hello?", parameters);
        //test.searchAndReplace02("?Hello?");
    }

    private static void printCall02(String sql, Object[] parameters) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            String classType = parameter.getClass().getSimpleName();
            sb.append(classType);
            sb.append(":");
            sb.append(parameter);
            sb.append("\n");
        }
        sb.append(sql);
        sb.append("\n");
        System.out.println(sb.toString());
    }

    public static void printCall(String sql, Object[] parameters) {
        sql = sql.replace("{", "");
        sql = sql.replace("}", "");
        String patternStr = "\\?";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(sql);
        StringBuilder buf = new StringBuilder();
        boolean found = false;
        int i = 0;
        while ((found = matcher.find())) {
            Object parameter = parameters[i++];
            String classType = parameter.getClass().getSimpleName();
            if (classType.equals("String")) {
                StringBuilder sb = new StringBuilder();
                sb.append("'");
                sb.append(parameter.toString());
                sb.append("'");
                matcher.appendReplacement(buf, sb.toString());
            } else {
                matcher.appendReplacement(buf, parameter.toString());
            }
        }
        matcher.appendTail(buf);
        buf.append(";");
        String result = buf.toString();
        System.out.println(result);
    }

}
