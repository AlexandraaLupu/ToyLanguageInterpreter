package model.expression;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.types.RefType;
import model.types.Type;
import model.values.RefValue;
import model.values.Value;

public class ReadHeapExpression implements IExpression{
    private final IExpression expression;
    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException, ADTException {
        Type typ = expression.typeCheck(typeEnv);
        if(typ instanceof RefType) {
            RefType reft = (RefType) typ;
            return reft.getInner();
        }
        else throw new ExpressionException("The rH argument is not a RefType");
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionException, ADTException {
        Value value = expression.evaluate(symTable, heap);
        if (!(value instanceof RefValue))
            throw new ExpressionException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        return heap.get(refValue.getAddress());
    }

    @Override
    public IExpression deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("ReadHeap(%s)", expression);
    }
}
