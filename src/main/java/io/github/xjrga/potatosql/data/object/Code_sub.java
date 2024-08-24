package io.github.xjrga.potatosql.data.object;

public class Code_sub {
  private final String action;
  private final int hashCode;

  public Code_sub(String action, int hashCode) {
    this.action = action;
    this.hashCode = hashCode;
  }

  public String getAction() {
    return action;
  }

  @Override
  public String toString() {
    return action;
  }

  @Override
  public int hashCode() {
    return hashCode;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    return hashCode() == obj.hashCode();
  }
}
