package org.xjrga.potatosql.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Replacer {
    private final Pattern pattern_one;
    private final String first_string_to_replace = "'";
    private final String first_replacement_string = "''";
    private final Pattern pattern_two;
    private final String second_string_to_replace = "\"";
    private final String second_replacement_string = "'";
    private Matcher matcher_one;
    private Matcher matcher_two;

    public Replacer() {
        pattern_one = Pattern.compile(first_string_to_replace);
        pattern_two = Pattern.compile(second_string_to_replace);
    }

    public String replace(String input0) {
        matcher_one = pattern_one.matcher(input0);
        matcher_two = pattern_two.matcher(matcher_one.replaceAll(first_replacement_string));
        String[] fields = matcher_two.replaceAll(second_replacement_string).split(";", -1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isEmpty()) {
                sb.append("NULL");
            } else {
                sb.append(fields[i]);
            }
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
