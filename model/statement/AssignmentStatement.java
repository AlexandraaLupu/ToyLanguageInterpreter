package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIStack;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.types.Type;
import model.values.Value;

public class AssignmentStatement implements IStatement{
    private String key;
    private IExpression expression;

    public AssignmentStatement(String key, IExpression expression) {
        this.key = key;
        this.expression = expression;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementException, ADTException, ExpressionException {
        Type typeVar = typeEnv.lookUp(key);
        Type typeExp = expression.typeCheck(typeEnv);
        if (typeVar.equals(typeExp))
            return typeEnv;
        else
            throw new StatementException("Assignment: right hand side and left hand side have different types");
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();

        if (symTable.isDefined(key)) {
            Value val = expression.evaluate(symTable, state.getHeap());
            Type typeId = (symTable.lookUp(key)).getType();
            if(val.getType().equals(typeId)) {
                symTable.update(key, val);
            }
            else throw new StatementException("declared type of variable " + key + " and type of the assigned expression do not match");
        }
        else throw new StatementException("the used variable " + key + " was not declared before");
        state.setSymTable(symTable);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignmentStatement(key, expression.deepCopy());
    }

    @Override
    public String toString() {
        return key + " = " + expression.toString();
    }
}
