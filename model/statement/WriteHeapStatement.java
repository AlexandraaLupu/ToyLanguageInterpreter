package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.types.RefType;
import model.types.Type;
import model.values.RefValue;
import model.values.Value;

public class WriteHeapStatement implements IStatement{
    private String varName;
    private IExpression expression;

    public WriteHeapStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "WriteHeap(" + varName + ", " + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if (!symTable.isDefined(varName))
            throw new StatementException(String.format("%s not present in the symTable", varName));
        Value value = symTable.lookUp(varName);
        if (!(value instanceof RefValue))
            throw new StatementException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        Value evaluated = expression.evaluate(symTable, heap);
        if (!evaluated.getType().equals(refValue.getLocationType()))
            throw new StatementException(String.format("%s not of %s", evaluated, refValue.getType()));
        heap.update(refValue.getAddress(), evaluated);
        state.setHeap(heap);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementException, ADTException, ExpressionException {
        if (typeEnv.lookUp(varName).equals(new RefType(expression.typeCheck(typeEnv))))
            return typeEnv;
        else
            throw new StatementException("WriteHeap: right hand side and left hand side have different types");
    }

    @Override
    public IStatement deepCopy() {
        return new WriteHeapStatement(varName, expression.deepCopy());
    }
}
