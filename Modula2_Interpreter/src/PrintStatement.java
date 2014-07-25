


public class PrintStatement implements Statement{
    private Id id;
    public PrintStatement(Id id){
        if (id == null)
            throw new NullPointerException("Null Id in PrintStatement"+id);
        this.id = id;
    }
    @Override
    public void execute() {
        System.out.println(id.evaluate());
    }
}
