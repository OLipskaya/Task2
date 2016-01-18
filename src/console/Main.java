package console;

import console.control.CommandControl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandControl control = new CommandControl();
        Scanner scanner = new Scanner(System.in);
        control.setProperty(System.getProperty("os.name"));
        int num = 1;
        while (num < 2) {
            System.out.print("> ");
            String inputLine = scanner.nextLine();
            if(!inputLine.equals("end")){
                control.setInputLine(inputLine);
                System.out.print(control.getResult());
            } else { num++; }
        }
    }
}