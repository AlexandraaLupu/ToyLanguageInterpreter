package model.values;

import model.types.RefType;
import model.types.Type;

public class RefValue implements Value {
    private int address;
    Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }


    @Override
    public String toString() {
        return address + ", " + locationType.toString();
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    public Type getLocationType() {
        return locationType;
    }

    public int getAddress() {
        return address;
    }

    @Override
    public Value deepCopy() {
        return new RefValue(address, locationType.deepCopy());
    }
}
