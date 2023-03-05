package model.expression;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

public class RelationalExpression implements IExpression {
    IExpression expression1;
    IExpression expression2;
    String operator;

    public RelationalExpression(IExpression expression1, IExpression expression2, String operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException, ADTException {
        Type typ1, typ2;
        typ1 = expression1.typeCheck(typeEnv);
        typ2 = expression2.typeCheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else throw new ExpressionException("Second operand is not an integer");
        } else throw new ExpressionException("First operand is not an integer");
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operator + " " + expression2.toString();
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionException, ADTException {
        Value result1, result2;
        result1 = expression1.evaluate(symTable, heap);
        if (result1.getType().equals(new IntType())) {
            result2 = expression2.evaluate(symTable, heap);
            if (result2.getType().equals(new IntType())) {
                IntValue value1 = (IntValue) result1;
                IntValue value2 = (IntValue) result2;
                int v1, v2;
                v1 = value1.getValue();
                v2 = value2.getValue();
                if (operator.equals("<"))
                    return new BoolValue(v1 < v2);
                else if (operator.equals("<="))
                    return new BoolValue(v1 <= v2);
                else if (operator.equals("=="))
                    return new BoolValue(v1 == v2);
                else if (operator.equals("!="))
                    return new BoolValue(v1 != v2);
                else if (operator.equals(">"))
                    return new BoolValue(v1 > v2);
                else if (operator.equals(">="))
                    return new BoolValue(v1 >= v2);
            } else
                throw new ExpressionException("Second operand is not int");
        } else throw new ExpressionException("First operand is not int");
        return null;
    }

    @Override
    public IExpression deepCopy() {
        return new RelationalExpression(expression1.deepCopy(), expression2.deepCopy(), operator);
    }
}
