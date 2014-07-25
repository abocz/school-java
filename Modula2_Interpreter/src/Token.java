

public class Token {
    private TokenType tokType;
    private String lexeme;
    private int rowNum;
    private int columnNum;

    public Token(int rowNum, int columnNum, String lexeme, TokenType tokType)
    {
        if (lexeme == null || lexeme.length() == 0)
            throw new IllegalArgumentException ("Null String/Lexeme Argument in Token");
        if (rowNum <= 0)
            throw new IllegalArgumentException ("Invalid Line Number in Token");
        if (columnNum <= 0)
            throw new IllegalArgumentException ("Invalid Column Number in Token");
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.lexeme = lexeme;
        this.tokType = tokType;
    }

    public TokenType getTokenType()
    {
        return tokType;
    }

    public String getLexeme()
    {
        return lexeme;
    }

    public int getLineNumber()
    {
        return rowNum;
    }

    public int getColumnNumber()
    {
        return columnNum;
    }

    @Override
    public String toString()
    {
        return tokType + ": " + lexeme + " row: " + rowNum + " column: " + columnNum;
    }
}

