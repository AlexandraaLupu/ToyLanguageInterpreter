package model.types;

import model.values.RefValue;
import model.values.Value;

public class RefType implements Type{
    private Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public Value getDefaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public boolean equals(Type anotherType) {
        if (anotherType instanceof  RefType)
            return inner.equals(((RefType) anotherType).getInner());
        else
            return false;
    }

    @Override
    public Type deepCopy() {
        return new RefType(inner.deepCopy());
    }
}
