import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LexicalAnalyzer {
    private List<Token> tokens;

    public LexicalAnalyzer(String fileName) throws FileNotFoundException, LexicalException
    {
        if (fileName == null)
            throw new IllegalArgumentException("Null filename");
        tokens = new ArrayList<Token>();
        Scanner sourceCode = new Scanner (new File (fileName));
        int lineNumber = 0;
        while (sourceCode.hasNext())
        {
            String line = sourceCode.nextLine();
            processLine (line, lineNumber);
            lineNumber++;
        }
        tokens.add(new Token (lineNumber, 1, "EOS", TokenType.EOS_TOK));
        sourceCode.close();
    }

    private void processLine(String line, int lineNumber) throws LexicalException
    {
        int index = 0;
        index = skipWhiteSpace (line, index);
        while (index < line.length())
        {
            String lexeme = getLexeme (line, lineNumber, index);
            TokenType tokType = getTokenType (lexeme, lineNumber, index);
            tokens.add(new Token (lineNumber + 1, index + 1, lexeme, tokType));
            index += lexeme.length();
            index = skipWhiteSpace (line, index);
        }
    }

    private TokenType getTokenType(String lexeme, int lineNumber, int columnNumber) throws LexicalException
    {
        TokenType tokType;
        /*
        Letter tokens include MODULE, BEGIN, END, IF, THEN, ELSE, WHILE, DO, WriteInt, REPEAT, UNTIL, and ID
         */
        if (Character.isLetter(lexeme.charAt(0)))
        {
            if (lexeme.length() == 1)
                if (isValidIdentifier (lexeme.charAt(0)))
                    tokType = TokenType.ID_TOK;
                else
                    throw new LexicalException ("invalid lexeme at row number " +
                            (lineNumber + 1) + " and column " + (columnNumber + 1));
            else if (lexeme.length() > 1){
                if (lexeme.equalsIgnoreCase("module")){
                    tokType = TokenType.MOD_TOK;
                }
                else if (lexeme.equalsIgnoreCase("begin")){
                    tokType = TokenType.BEGIN_TOK;
                }
                else if (lexeme.equalsIgnoreCase("end")){
                    tokType = TokenType.END_TOK;
                }
                else if (lexeme.equalsIgnoreCase("if")){
                    tokType = TokenType.IF_TOK;
                }
                else if (lexeme.equalsIgnoreCase("then")){
                    tokType = TokenType.THEN_TOK;
                }
                else if (lexeme.equalsIgnoreCase("else")){
                    tokType = TokenType.ELSE_TOK;
                }
                else if (lexeme.equalsIgnoreCase("while")){
                    tokType = TokenType.WHILE_TOK;
                }
                else if (lexeme.equalsIgnoreCase("do")){
                    tokType = TokenType.DO_TOK;
                }
                else if (lexeme.equalsIgnoreCase("writeint")){
                    tokType = TokenType.WRITEINT_TOK;
                }
                else if (lexeme.equalsIgnoreCase("repeat")){
                    tokType = TokenType.REPEAT_TOK;
                }
                else if (lexeme.equalsIgnoreCase("until")){
                    tokType = TokenType.UNTIL_TOK;
                }
                else
                    throw new LexicalException("Invalid Letter Lexeme at row number " +
                            (lineNumber + 1) + "and column " + (columnNumber + 1));
            }
            else
                throw new LexicalException ("invalid lexeme at row number " +
                        (lineNumber + 1) + " and column " + (columnNumber + 1));
        }
        else if (Character.isDigit (lexeme.charAt(0)))
        {
            if (allDigits (lexeme))
                tokType = TokenType.LITINT_TOK;
            else
                throw new LexicalException ("invalid lexeme at row number " +
                        (lineNumber + 1) + " and column " + (columnNumber + 1));
        }
        else if (lexeme.equals(":="))
            tokType = TokenType.ASSIGN_TOK;
        else if (lexeme.equals("<="))
            tokType = TokenType.LE_TOK;
        else if (lexeme.equals("<"))
            tokType = TokenType.LT_TOK;
        else if (lexeme.equals(">="))
            tokType = TokenType.GE_TOK;
        else if (lexeme.equals(">"))
            tokType = TokenType.GT_TOK;
        else if (lexeme.equals("="))
            tokType = TokenType.EQ_TOK;
        else if (lexeme.equals("#"))
            tokType = TokenType.NE_TOK;
        else if (lexeme.equals("+"))
            tokType = TokenType.ADD_TOK;
        else if (lexeme.equals("-"))
            tokType = TokenType.SUB_TOK;
        else if (lexeme.equals("*"))
            tokType = TokenType.MUL_TOK;
        else if (lexeme.equals("/"))
            tokType = TokenType.DIV_TOK;
        else if (lexeme.equals("("))
            tokType = TokenType.LPAREN_TOK;
        else if (lexeme.equals(")"))
            tokType = TokenType.RPAREN_TOK;
        else if (lexeme.equals(";"))
            tokType = TokenType.SEMIC_TOK;
        else if (lexeme.equals("."))
            tokType = TokenType.PERIOD_TOK;

        else
            throw new LexicalException ("No token match, invalid lexeme at row number " +
                    (lineNumber + 1) + " and column " + (columnNumber + 1));
        return tokType;
    }

    private boolean allDigits(String s)
    {
        int i = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i)))
            i++;
        return i == s.length();
    }

    private String getLexeme(String line, int lineNumber, int index)
    {
        int i = index;
        while (i < line.length() && !Character.isWhitespace(line.charAt(i)))
            i++;
        return line.substring(index, i);
    }

    private int skipWhiteSpace(String line, int index)
    {
        while (index < line.length() && Character.isWhitespace(line.charAt(index)))
            index++;
        return index;
    }

    public Token getNextToken() throws LexicalException
    {
        if (tokens.isEmpty())
            throw new LexicalException ("no more tokens");
        return tokens.remove(0);
    }

    public Token getLookaheadToken() throws LexicalException
    {
        if (tokens.isEmpty())
            throw new LexicalException ("no more tokens");
        return tokens.get(0);
    }

    public static boolean isValidIdentifier (char ch)
    {
        //Work Here?  For now just always return true...
        return true;
    }

}
