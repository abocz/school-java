import java.io.FileNotFoundException;

public class Program {
    private StatementSequence mainSequence;
    public Program(Id var, StatementSequence mainSequence){
        if(mainSequence == null){
            throw new NullPointerException("Null sequence from Program");
        }
        this.mainSequence = mainSequence;
    }
    public void execute(){
        mainSequence.execute();
    }
}
