package console.control;

import console.commands.Command;

public class CommandAction {
    private String result = "";

    public CommandAction() {}

    private void loadClass(String className, String param) {
        try {
            Class cmdClass = Class.forName(className);
            Object obj = cmdClass.newInstance();
            if (obj instanceof Command) {
                ((Command) obj).setParametrs(param);
                ((Command) obj).execution();
                result = ((Command) obj).printResponse();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void execution(String name, String request) {
        loadClass(name, request);
    }

    public String getResult() {
        return result;
    }
}



