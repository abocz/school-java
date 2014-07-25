

public class LiteralInteger implements Expression{
    private int value;

    public LiteralInteger(int value)
    {
        this.value = value;
    }

    public int evaluate ()
    {
        return value;
    }
}
