package crm.entity;

import javax.persistence.Column;

import java.util.Objects;

import static crm.util.ArgumentValidator.validate;

public class NipNumber {

    @Column(name = "NipNumber")
    private String value;

    private NipNumber(){};


    public NipNumber(String value) {
        validate(value != null && value.matches("\\d{10}"), "Invalid NIP");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NipNumber nipNumber = (NipNumber) o;
        return value.equals(nipNumber.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
