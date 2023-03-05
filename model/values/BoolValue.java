package model.values;

import model.types.BoolType;
import model.types.Type;

public class BoolValue implements Value{
    boolean value;

    public BoolValue() {
        value = false;
    }

    public BoolValue(boolean value) {
        this.value = value;
    }

    public BoolValue and(BoolValue value2) {
        return new BoolValue(this.value && value2.value);
    }

    public BoolValue or(BoolValue value2) {
        return new BoolValue(this.value || value2.value);
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(value);
    }

    public boolean getValue(){
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
