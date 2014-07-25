import java.util.List;

public class StatementSequence {
    private List<Statement> stmnts;
    public StatementSequence(List<Statement> statementList){
        stmnts = statementList;
    }
    public void execute(){
        for (Statement s: stmnts)
            s.execute();
    }
}
