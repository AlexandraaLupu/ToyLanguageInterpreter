package model.expression;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

public class NotExpression implements IExpression{
    private IExpression expression;

    public NotExpression(IExpression expression) {

        this.expression = expression;
    }

    @Override
    public String toString() {
        return "!(" + expression.toString() + ")";

    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException, ADTException {
        return expression.typeCheck(typeEnv);
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionException, ADTException {
        BoolValue value = (BoolValue) expression.evaluate(symTable, heap);
        if (value.getValue())
            return new BoolValue(false);
        else
            return new BoolValue(true);
    }

    @Override
    public IExpression deepCopy() {

        return new NotExpression(expression.deepCopy());
    }
}
