

public class Memory {
    private static int[] mem = new int[52];

    public static int fetch (char ch){
        return mem[getIndex(ch)];
    }

    public static void store (char ch, int value)
    {
        mem[getIndex(ch)] = value;
    }

    private static int getIndex (char ch)
    {
        int index;
        if (Character.isUpperCase(ch))
            index = ch - 'A';
        else
            index = ch - 'a' + 26;
        return index;
    }
}
