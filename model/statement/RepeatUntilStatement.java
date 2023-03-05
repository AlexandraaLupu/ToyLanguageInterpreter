package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIStack;
import model.expression.IExpression;
import model.expression.NotExpression;
import model.programState.ProgramState;
import model.types.BoolType;
import model.types.Type;

public class RepeatUntilStatement implements IStatement {
    private final IStatement statement;
    private final IExpression expression;

    public RepeatUntilStatement(IStatement statement, IExpression expression) {
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "repeat(" + statement.toString() + ") until " + expression.toString() + ")";
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementException, ADTException, ExpressionException {
        Type type = expression.typeCheck(typeEnv);
        if (type.equals(new BoolType())) {
            statement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        } else {
            throw new StatementException("Expression does not have the type bool");
        }
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        MyIStack<IStatement> exeStack = state.getExeStack();
        IStatement statement1 = new CompoundStatement(statement, new WhileStatement(new NotExpression(expression), statement));
        exeStack.push(statement1);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new RepeatUntilStatement(statement.deepCopy(), expression.deepCopy());
    }
}
