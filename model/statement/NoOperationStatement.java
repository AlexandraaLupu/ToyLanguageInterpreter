package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADTs.MyIDictionary;
import model.programState.ProgramState;
import model.types.Type;

public class NoOperationStatement implements IStatement{
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementException, ADTException, ExpressionException {
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new NoOperationStatement();
    }

    @Override
    public String toString() {
        return "NoOperationStatement";
    }
}

