package crm.entity;

import javax.persistence.Column;
import java.util.Objects;

import static crm.util.ArgumentValidator.validate;


public class SsNumber {

    @Column(name = "SsNumber")//give name for column
    private String value;

    private SsNumber(){}; //private constructor for hibernate dont use!

    public SsNumber(String value) {
        validate(value != null && value.matches("\\d{11}"), "SsNumber is invadlid" + value);
        this.value = value;
    }

    public String getValue() {
        return value;
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

    @Override
    public String toString() {
        return value;
    }
}
