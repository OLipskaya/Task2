package console.commands;

import java.io.File;
import java.io.FileFilter;

public class FilesList implements Command {
    private String response = "";
    private String filePath = "";

    public FilesList() {}

    @Override
    public String printResponse() {
        return response;
    }

    @Override
    public void execution() {
        StringBuffer buffer = new StringBuffer();
        File file = new File(filePath);
        FileFilter ff = new FileFilter(){
            @Override
            public boolean accept(File name) {
                if(name.isFile()||name.isDirectory()){ return true; }
                return false;
            }
        };
        File[] list = file.listFiles(ff);
        if(list.length != 0){
            for(File f : list){
                buffer.append(f.getAbsolutePath()+"\n");
            }
            response = buffer.toString();
        }
    }

    @Override
    public void setParametrs(String param) {
        param = param.substring(0, param.lastIndexOf("/")+1);
        filePath = param;
    }
}
