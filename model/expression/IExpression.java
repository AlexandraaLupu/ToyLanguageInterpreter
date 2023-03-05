package model.expression;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.programState.ProgramState;
import model.types.Type;
import model.values.Value;

public interface IExpression {

    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException, ADTException;
    Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionException, ADTException;
    IExpression deepCopy();
}
