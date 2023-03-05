package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import javafx.util.Pair;
import model.ADTs.MyIBarrierTable;
import model.ADTs.MyIDictionary;
import model.ADTs.MyIHeap;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.types.IntType;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStatement implements IStatement{

    private String var;
    private IExpression expression;
    private Lock lock = new ReentrantLock();

    public NewBarrierStatement(String var, IExpression expression) {
        this.var = var;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "newBarrier(" + var + ", " + expression + ")";
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementException, ADTException, ExpressionException {
        if (typeEnv.lookUp(var).equals(new IntType()))
            if (expression.typeCheck(typeEnv).equals(new IntType()))
                return typeEnv;
            else
                throw new ADTException("expression not of type int");
        else
            throw new ADTException("variable not of type int");
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        lock.lock();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        MyIBarrierTable barrierTable = state.getBarrierTable();
        IntValue n = (IntValue) (expression.evaluate(symTable, heap));
        int nr = n.getValue();
        int newFreeLocation = barrierTable.getFreeAddress();
        barrierTable.put(newFreeLocation, new Pair<>(nr, new ArrayList<>()));
        if (symTable.isDefined(var))
            symTable.update(var, new IntValue(newFreeLocation));
        else
            throw new ADTException(var + "is not defined in the symTable");
        lock.unlock();
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NewBarrierStatement(var, expression.deepCopy());
    }
}
