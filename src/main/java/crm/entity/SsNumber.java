package crm.entity;

import java.util.Objects;

import static crm.util.ArgumentValidator.validate;

public class SsNumber {
    private String value;

    public SsNumber(String value) {
        validate(value != null || value.matches("\\d{11}"), "SsNumber is invadlid" + value);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SsNumber ssNumber = (SsNumber) o;
        return value.equals(ssNumber.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
