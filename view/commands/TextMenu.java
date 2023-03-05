package view.commands;

import exceptions.ADTException;
import model.ADTs.MyDictionary;
import model.ADTs.MyIDictionary;

import java.util.Scanner;

public class TextMenu {
    private MyIDictionary<String, Command> commands;

    public TextMenu() {
        commands = new MyDictionary<>();
    }

    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }

    private void printMenu() {
        for (Command com : commands.getValues()) {
            String line = String.format("%4s:%s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.printf("Input the option: ");
            String key = scanner.nextLine();
            try {
                Command com = commands.lookUp(key);
                com.execute();
            } catch (ADTException e) {
                System.out.println("Invalid option");
            }
        }
    }
}
