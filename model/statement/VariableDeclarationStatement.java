package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADTs.MyIDictionary;
import model.programState.ProgramState;
import model.types.Type;
import model.values.Value;

public class VariableDeclarationStatement implements IStatement{
    String name;
    Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(symTable.isDefined(name))
            throw new StatementException("Variable " + name + "already exists in the symTable");
        symTable.put(name, type.getDefaultValue());
        state.setSymTable(symTable);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementException, ADTException, ExpressionException {
        typeEnv.put(name, type);
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new VariableDeclarationStatement(name, type);
    }

    @Override
    public String toString() {
        return name + " " + type.toString();
    }
}
