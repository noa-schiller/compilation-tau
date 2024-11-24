import java.text.MessageFormat;

public enum TokenNames {
  /* terminals */
  EOF,
  LPAREN,
  RPAREN,
  LBRACK,
  RBRACK,
  RBRACE,
  LBRACE,
  NIL,
  PLUS,
  MINUS,
  TIMES,
  DIVIDE,
  COMMA,
  DOT,
  SEMICOLON,
  TYPE_INT,
  TYPE_VOID,
  TYPE_STRING,
  ASSIGN,
  EQ,
  LT,
  GT,
  ARRAY,
  CLASS,
  EXTENDS,
  RETURN,
  WHILE,
  IF,
  NEW,
  INT,
  STRING,
  ID;

  public static TokenNames fromOrdinal(int ord) {
    for (TokenNames t : TokenNames.values()) {
      if (t.ordinal() == ord) {
        return t;
      }
    }
    throw new IllegalArgumentException(MessageFormat.format("Invalid token ordinal: {0}", ord));
  }
}
