package model.expression;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.types.IntType;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;

public class ArithmeticExpression implements IExpression{
    IExpression expression1;
    IExpression expression2;
    char operation;

    public ArithmeticExpression(char op, IExpression exp1, IExpression exp2) {
        operation = op;
        expression1 = exp1;
        expression2 = exp2;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException, ADTException {
        Type typ1, typ2;
        typ1 = expression1.typeCheck(typeEnv);
        typ2 = expression2.typeCheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            }
            else throw new ExpressionException("Second operand is not an integer");
        } else throw new ExpressionException("First operand is not an integer");
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionException, ADTException {
        Value v1, v2;
        v1 = expression1.evaluate(symTable, heap);
        if(v1.getType().equals(new IntType())) {
            v2 = expression2.evaluate(symTable, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (operation == '+')
                    return new IntValue(n1 + n2);
                if (operation == '-')
                    return new IntValue(n1 -n2);
                if (operation == '*')
                    return new IntValue(n1 * n2);
                if (operation == '/') {
                    if (n2 == 0)
                        throw new ExpressionException("division by zero");
                    else return new IntValue(n1 / n2);
                }
                else throw new ExpressionException("second operand is not an integer");
            }
            else throw new ExpressionException("first operand is not an integer");
        }
        return null;
    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticExpression(operation, expression1, expression2);
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operation + " " + expression2.toString();
    }
}
