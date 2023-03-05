package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ADTs.MyIDictionary;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.types.IntType;
import model.types.StringType;
import model.types.Type;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private IExpression expression;
    private String varName;

    public ReadFile(IExpression expression, String varName) {
        this.expression = expression;
        this.varName = varName;
    }

    @Override
    public String toString() {
        return "ReadFile(" + expression.toString() + ", " + varName + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();

        if (symTable.isDefined(varName)) {
            Value value = symTable.lookUp(varName);
            if (value.getType().equals(new IntType())) {
                Value fileNameValue = expression.evaluate(symTable, state.getHeap());
                if (fileNameValue.getType().equals(new StringType())) {
                    StringValue castValue = (StringValue)fileNameValue;
                    if (fileTable.isDefined(castValue.getValue())) {
                        BufferedReader br = fileTable.lookUp(castValue.getValue());
                        try {
                            String line = br.readLine();
                            if (line == null)
                                line = "0";
                            symTable.put(varName, new IntValue(Integer.parseInt(line)));
                        } catch (IOException e) {
                            throw new StatementException(String.format("Could not read from file %s", castValue));
                        }
                    } else {
                        throw new StatementException(String.format("The file table does not contain %s", castValue));
                    }
                } else {
                    throw new StatementException(String.format("%s does not evaluate to StringType", value));
                }
            } else {
                throw new StatementException(String.format("%s is not of type IntType", value));
            }
        } else {
            throw new StatementException(String.format("%s is not present in the symTable.", varName));
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementException, ADTException, ExpressionException {
        if(expression.typeCheck(typeEnv).equals(new StringType())) {
            if(typeEnv.lookUp(varName).equals(new IntType()))
                return typeEnv;
            else
                throw new StatementException("ReadFile requires an int as its variable parameter");
        }
        else
            throw new StatementException("ReadFile requires a string as es expression parameter");
    }

    @Override
    public IStatement deepCopy() {
        return new OpenRFile(expression.deepCopy());
    }
}
