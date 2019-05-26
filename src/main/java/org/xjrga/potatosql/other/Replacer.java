package org.xjrga.potatosql.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Replacer
{
    private Pattern pattern_one;
    private Matcher matcher_one;
    private String first_string_to_replace = "'";
    private String first_replacement_string = "''";
    private Pattern pattern_two;
    private Matcher matcher_two;
    private String second_string_to_replace = "\"";
    private String second_replacement_string = "'";

    public Replacer(){

        pattern_one = Pattern.compile(first_string_to_replace);
        pattern_two = Pattern.compile(second_string_to_replace);

    }

    public String replace(String input0){

        matcher_one = pattern_one.matcher(input0);
        matcher_two = pattern_two.matcher(matcher_one.replaceAll(first_replacement_string));

        return matcher_two.replaceAll(second_replacement_string);
    }
}
