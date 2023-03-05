package view.commands;

import controller.Controller;
import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;

import java.io.IOException;
import java.util.Scanner;

public class RunExample extends Command {
    private final Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Do you want to display the steps?");
            System.out.println("1. yes");
            System.out.println("2. no");
            Scanner readOption = new Scanner(System.in);
            int option = readOption.nextInt();
            if(option == 1)
                controller.setDisplayFlag(true);
            controller.allSteps();
        } catch (ExpressionException | ADTException | StatementException | IOException | InterruptedException e) {
            System.out.println("----" + e.getMessage() + "----");
        }
    }
}
