

public class WhileStatement implements Statement{
    private BooleanExpression boolExpr;
    private StatementSequence ss;
    public WhileStatement(BooleanExpression boolExpr, StatementSequence ss) {
        assert boolExpr != null;
        assert ss != null;
        this.boolExpr = boolExpr;
        this.ss = ss;
    }

    //working?
    @Override
    public void execute() {
        while (boolExpr.evaluate()){
            ss.execute();
        }

    }
}
