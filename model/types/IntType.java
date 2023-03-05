package model.types;

import model.values.IntValue;
import model.values.Value;

public class IntType  implements Type{
    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Value getDefaultValue() {
        return new IntValue(0);
    }

    @Override
    public boolean equals(Type anotherType) {
        return anotherType instanceof IntType;
    }

    @Override
    public Type deepCopy() {
        return new IntType();
    }
}
