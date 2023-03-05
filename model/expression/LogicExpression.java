package model.expression;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

public class LogicExpression implements IExpression {
    IExpression expression1;
    IExpression expression2;
    int operation;

    public LogicExpression(IExpression expression1, IExpression expression2, int operation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException, ADTException {
        Type typ1, typ2;
        typ1 = expression1.typeCheck(typeEnv);
        typ2 = expression2.typeCheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else throw new ExpressionException("Second operand is not bool");
        } else throw new ExpressionException("First operand is not an bool");
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionException, ADTException {
        Value v1, v2;
        v1 = expression1.evaluate(symTable, heap);
        if (v1.getType().equals(new BoolType())) {
            v2 = expression2.evaluate(symTable, heap);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = b1.getValue();
                n2 = b2.getValue();
                if (operation == 1)
                    return new BoolValue(n1 && n2);
                else if (operation == 2)
                    return new BoolValue(n1 || n2);
            } else throw new ExpressionException("second operand in not bool");
        } else throw new ExpressionException("first operand is not bool");
        return null;
    }

    @Override
    public IExpression deepCopy() {
        return new LogicExpression(expression1.deepCopy(), expression2.deepCopy(), operation);
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operation + " " + expression2.toString();
    }
}
