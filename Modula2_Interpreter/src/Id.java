

public class Id implements Expression
{
    private char ch;

    public Id(char ch)
    {
        if (!LexicalAnalyzer.isValidIdentifier(ch))
            throw new IllegalArgumentException ("Character is not a valid ID");
        this.ch = ch;
    }

    @Override
    public int evaluate()
    {
        return Memory.fetch (ch);
    }

    public char getChar ()
    {
        return ch;
    }
}
