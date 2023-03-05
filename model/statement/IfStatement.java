package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIStack;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.types.BoolType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

public class IfStatement implements IStatement {
    IExpression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        Value result = expression.evaluate(state.getSymTable(), state.getHeap());
        if (result.getType().equals(new BoolType())) {
            IStatement statement;
            BoolValue val = (BoolValue) result;
            if (val.getValue()) {
                statement = thenStatement;
            } else statement = elseStatement;
            MyIStack<IStatement> stack = state.getExeStack();
            stack.push(statement);
            state.setExeStack(stack);
            return null;
        } else throw new StatementException("provide a boolean expression for the if statement");
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementException, ADTException, ExpressionException {
        Type typeExp = expression.typeCheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            thenStatement.typeCheck(typeEnv.deepCopy());
            elseStatement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new StatementException("The condition of IF does not have the type bool");
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }

    @Override
    public String toString() {
        return "if " + expression + " {" + thenStatement + "} " + "else {" + elseStatement + "}";
    }
}
