

public class UntilStatement implements Statement{
    private BooleanExpression boolExpr;
    private StatementSequence ss;

    public UntilStatement(StatementSequence ss, BooleanExpression boolExpr) {
        this.boolExpr = boolExpr;
        this.ss = ss;
    }

    @Override
    public void execute() {
        do{
            ss.execute();
        }while(!boolExpr.evaluate());
    }
}
