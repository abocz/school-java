

public class AssignmentStatement implements Statement{
    private Id var;
    private Expression expr;

    public AssignmentStatement (Id var, Expression expr)
    {
        if (var == null)
            throw new IllegalArgumentException ("Null Id argument");
        if (expr == null)
            throw new IllegalArgumentException ("Null Expression argument");
        this.var = var;
        this.expr = expr;
    }
    public void execute()
    {
        Memory.store(var.getChar(), expr.evaluate());
    }
}
