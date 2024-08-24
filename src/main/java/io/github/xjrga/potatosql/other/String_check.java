package io.github.xjrga.potatosql.other;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class String_check {
  List<String> names = new LinkedList();

  public String_check() {}

  public void add(String s) {
    names.add(s);
  }

  public boolean pass() {
    boolean pass = false;
    if (check()) {
      pass = true;
    }
    return pass;
  }

  private boolean check() {
    boolean pass = true;
    for (String s : names) {
      if (s.isEmpty()) {
        pass = false;
        break;
      } else {
        if (!check_against_regex(s)) {
          pass = false;
          break;
        }
      }
    }
    return pass;
  }

  private boolean check_against_regex(String s) {
    boolean pass = false;
    String patternStr = "[A-Za-z0-9_]+";
    Pattern pattern = Pattern.compile(patternStr);
    Matcher matcher = pattern.matcher(s);
    if (matcher.matches()) {
      pass = true;
    }
    return pass;
  }
}
