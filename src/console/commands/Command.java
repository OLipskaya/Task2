package console.commands;

public interface Command {
    public String printResponse();
    public void execution();
    public void setParametrs(String parametrs);
}
