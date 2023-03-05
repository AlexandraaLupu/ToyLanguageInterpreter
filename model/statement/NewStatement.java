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

public class NewStatement implements IStatement {
    String name;
    IExpression expression;

    public NewStatement(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "new(" + name + ", " + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if (!symTable.isDefined(name))
            throw new StatementException(String.format("%s not in symTable", name));
        Value varValue = symTable.lookUp(name);
        if (!(varValue.getType() instanceof RefType))
            throw new StatementException(String.format("%s in not of RefType", name));
        Value evaluated = expression.evaluate(symTable, heap);
        Type locationType = ((RefValue) varValue).getLocationType();
        if (!locationType.equals(evaluated.getType()))
            throw new StatementException(String.format("%s not of %s", name, evaluated.getType()));
        int newPosition = heap.add(evaluated);
        symTable.put(name, new RefValue(newPosition, locationType));
        state.setSymTable(symTable);
        state.setHeap(heap);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementException, ADTException, ExpressionException {
        Type typeVar = typeEnv.lookUp(name);
        Type typeExp = expression.typeCheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else throw new StatementException("NEW stmt: right hand side and left hand side have different types");
    }

    @Override
    public IStatement deepCopy() {
        return new NewStatement(name, expression.deepCopy());
    }
}
