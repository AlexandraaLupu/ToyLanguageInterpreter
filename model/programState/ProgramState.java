package model.programState;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADTs.*;
import model.statement.IStatement;
import model.values.Value;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

public class ProgramState {
    private MyIStack<IStatement> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;

    private MyIDictionary<String, BufferedReader> fileTable;

    MyIHeap heap;
    private MyIBarrierTable barrierTable;
    private IStatement originalProgram;
    private int id;
    private static int lastId = 0;

    public ProgramState(MyIStack<IStatement> stack, MyIDictionary<String, Value> dictionary, MyIList<Value> list,
                        MyIDictionary<String, BufferedReader> fileTable, MyIHeap heap, IStatement originalProgram, MyIBarrierTable barrierTable) {
        this.exeStack = stack;
        this.symTable = dictionary;
        this.out = list;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = originalProgram.deepCopy();
        this.exeStack.push(this.originalProgram);
        this.id = setId();
        this.barrierTable = barrierTable;

    }

    public ProgramState(MyIStack<IStatement> stack, MyIDictionary<String, Value> dictionary, MyIList<Value> list,
                        MyIDictionary<String, BufferedReader> fileTable, MyIHeap heap, MyIBarrierTable barrierTable) {
        this.exeStack = stack;
        this.symTable = dictionary;
        this.out = list;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = setId();
        this.barrierTable = barrierTable;

    }

    public MyIBarrierTable getBarrierTable() {
        return barrierTable;
    }

    public void setBarrierTable(MyIBarrierTable barrierTable) {
        this.barrierTable = barrierTable;
    }

    public synchronized int setId() {
        lastId++;
        return lastId;
    }

    public int getId() {
        return this.id;
    }

    public MyIHeap getHeap() {
        return heap;
    }


    public void setHeap(MyIHeap heap) {
        this.heap = heap;
    }

    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public Boolean isNotCompleted() {
        return exeStack.isEmpty();
    }

    public ProgramState oneStep() throws StatementException, ADTException, ExpressionException {
       if (exeStack.isEmpty())
            throw new StatementException("Program state stack is empty");
        IStatement currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

    public String exeStackToString() {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<IStatement> stack = exeStack.getReversed();
        for (IStatement statement : stack) {
            exeStackStringBuilder.append(statement.toString()).append("\n");
        }
        return exeStackStringBuilder.toString();
    }

    public String symTableToString() throws ADTException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key : symTable.getKeys()) {
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symTable.lookUp(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    public String outToString() {
        StringBuilder outStringBuilder = new StringBuilder();
        for (Value elem : out.getList()) {
            outStringBuilder.append(String.format("%s\n", elem.toString()));
        }
        return outStringBuilder.toString();
    }

    public String fileTableToString() {
        StringBuilder fileTableStringBuilder = new StringBuilder();
        for (String key : fileTable.getKeys()) {
            fileTableStringBuilder.append(String.format("%s\n", key));
        }
        return fileTableStringBuilder.toString();
    }

    public String heapToString() throws ADTException {
        StringBuilder heapStringBuilder = new StringBuilder();
        for (int key : heap.keySet()) {
            heapStringBuilder.append(String.format("%d -> %s\n", key, heap.get(key)));
        }
        return heapStringBuilder.toString();
    }

    public String barrierTableToString() throws ADTException {
        StringBuilder barrierStringBuilder = new StringBuilder();
        for (int key : barrierTable.keySet()) {
            barrierStringBuilder.append(String.format("%d -> %s\n", key, barrierTable.get(key)));
        }
        return barrierStringBuilder.toString();
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nExeStack:\n" + exeStack.getReversed() + "\n" +
                "SymTable:\n" + symTable.toString() + "\n" +
                "Out:\n" + out.toString() + "\n" +
                "FileTable:\n" + fileTable.toString() + "\n" +
                "Heap:\n" + heap.toString() + "\n" +
                "BarrierTable:\n" + barrierTable.toString() +
                "\n----------------------------------";
    }

    public String programStateToString() throws ADTException {
        return "ID: " + id + "\nExecution stack: \n" + exeStackToString() +
                "Symbol table: \n" + symTableToString() +
                "Output list: \n" + outToString() +
                "File table:\n" + fileTableToString() +
                "Heap:\n" + heapToString() +
                "BarrierTable:\n" + barrierTableToString();
    }
}
