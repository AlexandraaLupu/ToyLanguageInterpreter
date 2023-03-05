package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADTs.MyIDictionary;
import model.programState.ProgramState;
import model.types.Type;

public interface IStatement {

    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementException, ADTException, ExpressionException;
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException;
    IStatement deepCopy();
    String toString();
}
