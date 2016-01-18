package console.commands;

import java.io.File;

public class RelativePath implements Command {
    private String fileName = "";
    private String response = "";

    public RelativePath(){}

    @Override
    public void execution() {
        File file = new File(fileName);
        if(!file.equals(null)){
            response = file.getAbsolutePath()+"\n";
        }
    }
    @Override
    public void setParametrs(String parametrs) {
        if(!parametrs.equals(null)){
            fileName = parametrs;
        }
    }
    @Override
    public String printResponse() {
        return response;
    }

}
