package console.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessList implements Command {
    private String response = "";
    private String processName = "";
    private List<String> runningProcess;

    public ProcessList() {
        runningProcess = new ArrayList<>();
    }

    @Override
    public void execution() {
        try {
            Process proc = Runtime.getRuntime().exec("ps");
            InputStream stream = proc.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null){
                runningProcess.add(line);
            }
            parseList();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void parseList(){
        StringBuffer strBuffer = new StringBuffer();
        if(!processName.equals("")){
            strBuffer.append(runningProcess.get(0) + "\n");
            for(String pl: runningProcess){
                String prcName = pl.substring(pl.lastIndexOf(" ")+1,pl.length());
                if(processName.equals(prcName)){
                    strBuffer.append(pl+"\n");
                }
            }
        } else {
            for(String pl: runningProcess){
                strBuffer.append(pl+"\n");
            }
        }
        response = strBuffer.toString();
    }

    @Override
    public void setParametrs(String parametrs) {
        processName = parametrs;
    }

    @Override
    public String printResponse() {
        return response;
    }
}
