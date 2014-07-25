import java.io.File;
import java.io.FileNotFoundException;

public class Interpreter {
    public static void main(String[] args)throws ParserException{
        try{
            Parser p = new Parser("test3.mod");
            Program prog = p.parse();
            prog.execute();
        } catch (FileNotFoundException e) {
            System.out.println("File really not found...");
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        } catch (LexicalException e) {
            e.printStackTrace();
        }
    }
}
