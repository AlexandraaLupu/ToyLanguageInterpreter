package model.types;

import model.values.Value;

public interface Type {
    public Value getDefaultValue();
    public String toString();
    boolean equals(Type anotherType);

    Type deepCopy();
}
