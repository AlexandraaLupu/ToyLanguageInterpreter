//package view.commands;
//
//import controller.Controller;
//import exceptions.ADTException;
//import exceptions.ExpressionException;
//import exceptions.StatementException;
//import model.ADTs.MyDictionary;
//import model.ADTs.MyHeap;
//import model.ADTs.MyList;
//import model.ADTs.MyStack;
//import model.expression.*;
//import model.programState.ProgramState;
//import model.statement.*;
//import model.types.BoolType;
//import model.types.IntType;
//import model.types.RefType;
//import model.types.StringType;
//import model.values.BoolValue;
//import model.values.IntValue;
//import model.values.StringValue;
//import repository.IRepository;
//import repository.Repository;
//
//
//public class Interpreter {
//    public static void main(String[] args) {
//        TextMenu menu = new TextMenu();
//        menu.addCommand(new ExitCommand("0", "exit"));
//
//        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
//                        new PrintStatement(new VariableExpression("v"))));
//        try {
//            ex1.typeCheck(new MyDictionary<>());
//            ProgramState prg1 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex1);
//            IRepository repo1 = new Repository(prg1, "log1.txt");
//            Controller controller1 = new Controller(repo1);
//            menu.addCommand(new RunExample("1", ex1.toString(), controller1));
//        } catch (ExpressionException | StatementException | ADTException e) {
//            System.out.println(e);
//        }
//
//        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
//                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)), new
//                                ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
//                                new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"),
//                                        new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
//
//        try {
//            ex2.typeCheck(new MyDictionary<>());
//            ProgramState prg2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex2);
//            IRepository repo2 = new Repository(prg2, "log2.txt");
//            Controller controller2 = new Controller(repo2);
//            menu.addCommand(new RunExample("2", ex2.toString(), controller2));
//        } catch (ExpressionException | StatementException | ADTException e) {
//            System.out.println(e);
//        }
//
//        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
//                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
//                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
//                                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
//                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
//                                        new PrintStatement(new VariableExpression("v"))))));
//
//        try {
//            ex3.typeCheck(new MyDictionary<>());
//            ProgramState prg3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex3);
//            IRepository repo3 = new Repository(prg3, "log3.txt");
//            Controller controller3 = new Controller(repo3);
//            menu.addCommand(new RunExample("3", ex3.toString(), controller3));
//        } catch (ExpressionException | StatementException | ADTException e) {
//            System.out.println(e);
//        }
//
//        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
//                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
//                        new CompoundStatement(new OpenRFile(new VariableExpression("varf")),
//                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
//                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
//                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
//                                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
//                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
//                                                                        new CloseRFile(new VariableExpression("varf"))))))))));
//
//        try {
//            ex4.typeCheck(new MyDictionary<>());
//            ProgramState prg4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex4);
//            IRepository repo4 = new Repository(prg4, "log4.txt");
//            Controller controller4 = new Controller(repo4);
//            menu.addCommand(new RunExample("4", ex4.toString(), controller4));
//        } catch (ExpressionException | StatementException | ADTException e) {
//            System.out.println(e);
//        }
//
//        IStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
//                        new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                                new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(2))),
//                                        new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(3))),
//                                                new CompoundStatement(new IfStatement(new RelationalExpression(
//                                                        new VariableExpression("a"), new VariableExpression("b"), "<"),
//                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
//                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
//                                                        new PrintStatement(new VariableExpression("v"))))))));
//
//        try {
//            ex5.typeCheck(new MyDictionary<>());
//            ProgramState prg5 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex5);
//            IRepository repo5 = new Repository(prg5, "log5.txt");
//            Controller controller5 = new Controller(repo5);
//            menu.addCommand(new RunExample("5", ex5.toString(), controller5));
//        } catch (ExpressionException | StatementException | ADTException e) {
//            System.out.println(e);
//        }
//
//        IStatement ex6 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
//                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
//                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
//                                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
//                                                new PrintStatement(new ArithmeticExpression('+', new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));
//
//        try {
//            ex6.typeCheck(new MyDictionary<>());
//            ProgramState prg6 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex6);
//            IRepository repo6 = new Repository(prg6, "log6.txt");
//            Controller controller6 = new Controller(repo6);
//            menu.addCommand(new RunExample("6", ex6.toString(), controller6));
//        } catch (ExpressionException | StatementException | ADTException e) {
//            System.out.println(e);
//        }
//
//        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
//                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
//                                new CompoundStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
//                                        new PrintStatement(new ArithmeticExpression('+', new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))))));
//
//        try {
//            ex7.typeCheck(new MyDictionary<>());
//            ProgramState prg7 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex7);
//            IRepository repo7 = new Repository(prg7, "log7.txt");
//            Controller controller7 = new Controller(repo7);
//            menu.addCommand(new RunExample("7", ex7.toString(), controller7));
//        } catch (ExpressionException | StatementException | ADTException e) {
//            System.out.println(e);
//        }
//
//        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
//                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">"),
//                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                        new AssignmentStatement("v", new ArithmeticExpression(
//                                                '-', new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
//                                new PrintStatement(new VariableExpression("v")))));
//
//        try {
//            ex8.typeCheck(new MyDictionary<>());
//            ProgramState prg8 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex8);
//            IRepository repo8 = new Repository(prg8, "log8.txt");
//            Controller controller8 = new Controller(repo8);
//            menu.addCommand(new RunExample("8", ex8.toString(), controller8));
//        } catch (ExpressionException | StatementException | ADTException e) {
//            System.out.println(e);
//        }
//
//        IStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
//                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
//                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
//                                        new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(40))),
//                                                new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
//                                                        new PrintStatement(new ArithmeticExpression('+',
//                                                                new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),
//                                                                new ValueExpression(new IntValue(5))))))))));
//
//
//        try {
//            ex9.typeCheck(new MyDictionary<>());
//            ProgramState prg9 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex9);
//            IRepository repo9 = new Repository(prg9, "log9.txt");
//            Controller controller9 = new Controller(repo9);
//            menu.addCommand(new RunExample("9", ex9.toString(), controller9));
//        } catch (ExpressionException | StatementException | ADTException e) {
//            System.out.println(e);
//        }
//
//        IStatement ex10 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new IntType())),
//                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
//                                new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
//                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement("a",
//                                                new ValueExpression(new IntValue(30))), new CompoundStatement(new AssignmentStatement("v",
//                                                new ValueExpression(new IntValue(32))), new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))))),
//                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));
//
//        try {
//            ex10.typeCheck(new MyDictionary<>());
//            ProgramState prg10 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex10);
//            IRepository repo10 = new Repository(prg10, "log10.txt");
//            Controller controller10 = new Controller(repo10);
//            menu.addCommand(new RunExample("10", ex10.toString(), controller10));
//        } catch (ExpressionException | StatementException | ADTException e) {
//            System.out.println(e);
//        }
//
//
//        menu.show();
//
//    }
//}
