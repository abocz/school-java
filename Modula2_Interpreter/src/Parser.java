//rearrange, methods direct from class via stvn

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class Parser {
    private LexicalAnalyzer lex;

    public Parser(String fileName) throws FileNotFoundException, LexicalException, ParserException{
        if (fileName == null)
            throw new IllegalArgumentException("Null file");
        lex = new LexicalAnalyzer(fileName);
    }

    public Program parse() throws ParserException, LexicalException {
        Token tok = lex.getNextToken();
        match (tok, TokenType.MOD_TOK);
        Id var = getId();
        tok = lex.getNextToken();
        match (tok, TokenType.SEMIC_TOK);
        tok = lex.getNextToken();
        match (tok, TokenType.BEGIN_TOK);
        StatementSequence seq = getStatementSequence();
        tok = lex.getNextToken();
        match (tok, TokenType.END_TOK);
        Id var1 = getId();
        tok = lex.getNextToken();
        match (tok, TokenType.PERIOD_TOK);
        tok = lex.getNextToken();
        match(tok, TokenType.EOS_TOK);
        return new Program (var, seq);
    }
    private StatementSequence getStatementSequence() throws ParserException, LexicalException {
        StatementSequence smntSeq;
        List<Statement> statements = new LinkedList();
        while(lex.getLookaheadToken().getTokenType() != TokenType.END_TOK || lex.getLookaheadToken().getTokenType() != TokenType.UNTIL_TOK)
            statements.add(getStatement());
        smntSeq = new StatementSequence(statements);
        return smntSeq;
    }
    private Statement getStatement() throws ParserException, LexicalException {
        //<assignment_statement> | <print_statement> | <while_statement> | <if_statement> | <until_statement>
        Token tok = lex.getLookaheadToken();
        Statement stmnt;
        if (tok.getTokenType() == TokenType.IF_TOK)
            stmnt = getIfStatement();
        else if (tok.getTokenType() == TokenType.WHILE_TOK)
            stmnt = getWhileStatement();
        else if (tok.getTokenType() == TokenType.WRITEINT_TOK)
            stmnt = getPrintStatement();
        else if (tok.getTokenType() == TokenType.REPEAT_TOK)
            stmnt = getUntilStatement();
        //Determine assignment statement...
        else if (tok.getTokenType() == TokenType.ID_TOK)
            stmnt = getAssignmentStatement();
        else
            throw new ParserException("Error determining Statement type of "+ tok.toString());
        tok = lex.getNextToken();
        match(tok, TokenType.SEMIC_TOK);
        return stmnt;
    }
    private void match(Token tok, TokenType tokType) throws ParserException
    {
        if (tok.getTokenType() != tokType)
            throw new ParserException (tokType.name() + " expected at row " +
                    tok.getLineNumber() +" and column "  + tok.getColumnNumber());
    }
    private IfStatement getIfStatement() throws ParserException, LexicalException {
        Token tok = lex.getNextToken();
        match(tok, TokenType.IF_TOK);
        BooleanExpression boolExpr = getBooleanExpression();
        tok = lex.getNextToken();
        match(tok, TokenType.THEN_TOK);
        StatementSequence sSequence1 = getStatementSequence();
        tok = lex.getNextToken();
        match(tok, TokenType.ELSE_TOK);
        StatementSequence sSquence2 = getStatementSequence();
        tok = lex.getNextToken();
        match(tok, TokenType.END_TOK);
        return new IfStatement(boolExpr, sSequence1,sSquence2);
    }
    private PrintStatement getPrintStatement() throws ParserException, LexicalException {
        Token tok = lex.getNextToken();
        match(tok, TokenType.WRITEINT_TOK);
        tok = lex.getNextToken();
        match(tok, TokenType.LPAREN_TOK);
        Id id = getId();
        tok = lex.getNextToken();
        match(tok, TokenType.RPAREN_TOK);
        return new PrintStatement(id);
    }
    private WhileStatement getWhileStatement() throws ParserException, LexicalException {
        Token tok = lex.getNextToken();
        match(tok, TokenType.WHILE_TOK);
        BooleanExpression boolExpr = getBooleanExpression();
        tok = lex.getNextToken();
        match(tok, TokenType.DO_TOK);
        StatementSequence stmnts = getStatementSequence();
        tok = lex.getNextToken();
        match(tok, TokenType.END_TOK);
        return new WhileStatement(boolExpr, stmnts);
    }
    private UntilStatement getUntilStatement() throws ParserException, LexicalException {
        Token tok = lex.getNextToken();
        match(tok, TokenType.REPEAT_TOK);
        StatementSequence ss = getStatementSequence();
        tok = lex.getNextToken();
        match(tok, TokenType.UNTIL_TOK);
        BooleanExpression boolExpr = getBooleanExpression();
        return new UntilStatement(ss, boolExpr);
    }
    private AssignmentStatement getAssignmentStatement () throws ParserException, LexicalException {
        Id id = getId();
        Token tok = lex.getNextToken();
        match(tok, TokenType.ASSIGN_TOK);
        return new AssignmentStatement(id, getExpression());
    }
    private Id getId () throws ParserException, LexicalException {
        Token tok = lex.getNextToken();
        match (tok, TokenType.ID_TOK);
        return new Id (tok.getLexeme().charAt(0));
    }
    private Expression getExpression() throws ParserException, LexicalException {
        Expression expr = null;
        Token tok = lex.getLookaheadToken();
        if (tok.getTokenType() == TokenType.ADD_TOK||tok.getTokenType() == TokenType.SUB_TOK||tok.getTokenType() == TokenType.MUL_TOK||tok.getTokenType() == TokenType.DIV_TOK)
            expr = new BinaryExpression(getArithmeticOperator(),getExpression(), getExpression());
        else if (tok.getTokenType() == TokenType.ID_TOK)
            expr = getId();
        else if (tok.getTokenType() == TokenType.LITINT_TOK)
            expr = getLiteralInteger();
        return expr;
    }

    private ArithmeticOperator getArithmeticOperator() throws ParserException, LexicalException {
        Token tok = lex.getNextToken();
        if (tok.getTokenType() == TokenType.ADD_TOK)
            return ArithmeticOperator.ADD_OP;
        else if (tok.getTokenType() == TokenType.SUB_TOK)
            return ArithmeticOperator.SUB_OP;
        else if (tok.getTokenType() == TokenType.MUL_TOK)
            return ArithmeticOperator.MUL_OP;
        else if (tok.getTokenType() == TokenType.DIV_TOK)
            return ArithmeticOperator.DIV_OP;
        else
            throw new ParserException("Problem parsing Arithmetic Operator");
    }
    private RelationalOperator getRelationalOperator () throws ParserException, LexicalException {
        Token tok = lex.getNextToken();
        if (tok.getTokenType() == TokenType.LE_TOK)
            return RelationalOperator.LE_OP;
        else if (tok.getTokenType() == TokenType.LT_TOK)
            return RelationalOperator.LT_OP;
        else if (tok.getTokenType() == TokenType.GE_TOK)
            return RelationalOperator.GE_OP;
        else if (tok.getTokenType() == TokenType.GT_TOK)
            return RelationalOperator.GT_OP;
        else if (tok.getTokenType() == TokenType.EQ_TOK)
            return RelationalOperator.EQ_OP;
        else if (tok.getTokenType() == TokenType.NE_TOK)
            return RelationalOperator.NE_OP;
        else
            throw new ParserException("Problem parsing Relational Operator");
    }
    private LiteralInteger getLiteralInteger () throws ParserException, LexicalException {
        Token tok = lex.getNextToken();
        int value = 0;
        value = Integer.parseInt(tok.getLexeme());
        return new LiteralInteger(value);
    }
    private BooleanExpression getBooleanExpression () throws ParserException, LexicalException {
        RelationalOperator rOperator = getRelationalOperator();
        Expression ifExpr1 = getExpression();
        Expression ifExpr2 = getExpression();
        return new BooleanExpression(rOperator, ifExpr1, ifExpr2);
    }
}
