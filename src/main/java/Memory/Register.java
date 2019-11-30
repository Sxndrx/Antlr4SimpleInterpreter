package Memory;

public class Register {
    private int value;
    private boolean knownValue;

    public Register() {
        knownValue = false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (!knownValue)
            knownValue = true;
        this.value = value;
    }

    public boolean isKnownValue() {
        return knownValue;
    }

    public void setKnownValue(boolean knownValue) {
        this.knownValue = knownValue;
    }
}
