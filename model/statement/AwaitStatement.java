package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import javafx.util.Pair;
import model.ADTs.MyIBarrierTable;
import model.ADTs.MyIDictionary;
import model.programState.ProgramState;
import model.types.IntType;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitStatement implements IStatement {
    private String var;
    private Lock lock = new ReentrantLock();

    public AwaitStatement(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "await(" + var + ")";
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementException, ADTException, ExpressionException {
        if (typeEnv.lookUp(var).equals(new IntType()))
            return typeEnv;
        else
            throw new StatementException(var + " is not of type int");
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        lock.lock();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIBarrierTable barrierTable = state.getBarrierTable();
        if (symTable.isDefined(var)) {
            IntValue foundIndex = (IntValue) symTable.lookUp(var);
            int fi = foundIndex.getValue();
            if (barrierTable.containsKey(fi)) {
                Pair<Integer, List<Integer>> foundBarrier = barrierTable.get(fi);
                int N1 = foundBarrier.getKey();
                int NL = foundBarrier.getValue().size();
                ArrayList<Integer> L1 = (ArrayList<Integer>) foundBarrier.getValue();
                if (N1 > NL) {
                    if (L1.contains(state.getId()))
                        state.getExeStack().push(this);
                    else {
                        L1.add(state.getId());
                        barrierTable.update(fi, new Pair<>(N1, L1));
                        state.setBarrierTable(barrierTable);
                    }
                }
            } else {
                throw new StatementException(fi + " is not in the barrierTable");
            }
        } else {
            throw new StatementException(var + " is not defined in the symTable");
        }
        lock.unlock();
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AwaitStatement(var);
    }
}
