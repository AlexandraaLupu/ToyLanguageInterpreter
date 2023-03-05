package model.expression;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.types.Type;
import model.values.Value;

public class VariableExpression implements IExpression{
    String key;

    public VariableExpression(String key) {
        this.key = key;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException, ADTException {
        return typeEnv.lookUp(key);
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ADTException {
        return symTable.lookUp(key);
    }

    @Override
    public IExpression deepCopy() {
        return new VariableExpression(key);
    }

    @Override
    public String toString() {
        return key;
    }
}
