//package view;
//
//import controller.Controller;
//import exceptions.ADTException;
//import exceptions.ExpressionException;
//import exceptions.StatementException;
//import model.ADTs.*;
//import model.expression.ArithmeticExpression;
//import model.expression.ValueExpression;
//import model.expression.VariableExpression;
//import model.programState.ProgramState;
//import model.statement.*;
//import model.types.BoolType;
//import model.types.IntType;
//import model.types.StringType;
//import model.values.BoolValue;
//import model.values.IntValue;
//import model.values.StringValue;
//import model.values.Value;
//import repository.IRepository;
//import repository.Repository;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class View {
//    public void run() {
//        boolean done = false;
//        while(!done) {
//            try {
//                showMenu();
//                Scanner readOption = new Scanner(System.in);
//                int option = readOption.nextInt();
//                if (option == 0)
//                    done = true;
//                else if (option == 1)
//                    runProgram1();
//                else if(option == 2)
//                    runProgram2();
//                else if(option == 3)
//                    runProgram3();
//                else if(option == 4)
//                    runProgram4();
//                else
//                    System.out.println("Wrong option");
//            }
//            catch (ADTException | ExpressionException | StatementException | IOException e) {
//            System.out.println(e);}
//
//        }
//    }
//
//    private void runProgram1() throws IOException, ExpressionException, ADTException, StatementException{
//        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
//                        new PrintStatement(new VariableExpression("v"))));
//        runStatement(ex1);
//    }
//    private void runProgram2() throws IOException, ADTException, StatementException, ExpressionException {
//        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
//                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new
//                                ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
//                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new ValueExpression(new
//                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
//        runStatement(ex2);
//    }
//    private void runProgram3() throws IOException, ADTException, StatementException, ExpressionException {
//        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
//                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
//                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
//                                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
//                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
//                                        new PrintStatement(new VariableExpression("v"))))));
//        runStatement(ex3);
//    }
//
//    private void runProgram4() throws IOException, ADTException, StatementException, ExpressionException{
//        IStatement test = new CompoundStatement(
//                new VariableDeclarationStatement("varf",new StringType()),new CompoundStatement(
//                new AssignmentStatement("varf",new ValueExpression(new StringValue("test.in"))),new CompoundStatement(
//                new OpenRFile(new VariableExpression("varf")),new CompoundStatement(
//                new VariableDeclarationStatement("varc",new IntType()),new CompoundStatement(
//                new ReadFile(new VariableExpression("varf"),"varc"),new CompoundStatement(
//                new PrintStatement(new VariableExpression("varc")),new CompoundStatement(
//                new ReadFile(new VariableExpression("varf"),"varc") ,new CompoundStatement(
//                        new PrintStatement(new VariableExpression("varc")),new CloseRFile(new VariableExpression("varf"))))))))));
//        runStatement(test);
//    }
//
//    private void runStatement(IStatement ex1) throws IOException, ExpressionException, ADTException, StatementException{
//        MyIStack<IStatement> exeStack = new MyStack<>();
//        MyIDictionary<String, Value> symTable = new MyDictionary<>();
//        MyIList<Value> out = new MyList<>();
//        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
//        MyIHeap heap = new MyHeap();
//        ProgramState state = new ProgramState(exeStack, symTable, out, fileTable, heap,  ex1);
//
//        IRepository repository = new Repository(state, "log.txt");
//        Controller controller = new Controller(repository);
//        System.out.println("Do you want to see step by step?");
//        System.out.println("1. yes");
//        System.out.println("2. no");
//        Scanner readOption = new Scanner(System.in);
//        int option = readOption.nextInt();
//        if(option == 1)
//            controller.setDisplayFlag(true);
//        controller.allSteps();
//        System.out.println(state.getOut());
//    }
//
//
//
//    private void showMenu() {
//        System.out.println("Choose an option: ");
//        System.out.println("0. exit");
//        System.out.println("1. run program 1: int v; v=2;Print(v)");
//        System.out.println("2. run program 2: int a;int b; a=2+3*5;b=a+1;Print(b)");
//        System.out.println("3. run program 3: bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
//    }
//}
