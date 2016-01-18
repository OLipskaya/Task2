package console.control;

import java.io.File;

public class CommandControl {
    private XmlManager manager;
    private CommandAction commandAction;
    private String pathUnix = "src/console/Main.java";
    private String pathWin = "src\\console\\Main.java";
    private String result = "";
    private String path = "";
    private String path1 = "";

    public CommandControl() {
        manager = new XmlManager();
        commandAction = new CommandAction();
        path1 = new File("").getAbsolutePath();
    }

    public void setInputLine(String inputLine){
        String param = "";
        String command = "";
        if(inputLine.indexOf(" ") > 0) {
            String[] input = inputLine.split(" ");
            command = input[0];
            param = input[1];
        } else {
            command = inputLine;
        }
        parseCommand(command,param);
    }

    public void init(String command, String param) {
        String className = manager.getClassName(command);
        commandAction.execution(className, param);
        result = commandAction.getResult();
        if(result.equals(null)) {
            result = "error";
        }
    }

    public void parseCommand(String command, String param){
        switch (command) {
            case "ps":
                init(command, param);
                break;
            case "ls":
                init(command, path);
                break;
            case "pwd":
                init(command, path);
                break;
            default:
                result = command+" : command not found"+"\n";
                break;
        }
    }

    public void setProperty(String osName){
        pathUnix = path1+"/"+pathUnix;
        pathWin = path1+"\\"+pathWin;
        if(isWindows(osName)){
            setPath(pathWin,"win");
        } else {
            setPath(pathUnix,"unix");
        }
    }

    public void setPath(String pathName, String osName){
        path = pathName;
        manager.setProperty(osName);
    }

    public boolean isWindows(String osName){
        return (osName.indexOf("Windows") >= 0) ? true : false;
    }

    public String getResult(){
        return result;
    }
}
