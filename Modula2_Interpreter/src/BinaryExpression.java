


public class BinaryExpression implements Expression{
    private ArithmeticOperator op;
    private Expression expr1;
    private Expression expr2;

    public BinaryExpression(ArithmeticOperator op, Expression expr1, Expression expr2)
    {
        if (expr1 == null || expr2 == null)
            throw new IllegalArgumentException ("null expression argument");
        this.op = op;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public int evaluate()
    {
        int value = 0;
        switch (op)
        {
            case ADD_OP:
                value = expr1.evaluate() + expr2.evaluate();
                break;
            case SUB_OP:
                value = expr1.evaluate() - expr2.evaluate();
                break;
            case MUL_OP:
                value = expr1.evaluate() * expr2.evaluate();
                break;
            case DIV_OP:
                value = expr1.evaluate() / expr2.evaluate();
                break;
        }
        return value;
    }
}
