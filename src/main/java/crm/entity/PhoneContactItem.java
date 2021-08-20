package crm.entity;

import crm.util.ArgumentValidator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

import static crm.util.ArgumentValidator.*;

@Entity
@DiscriminatorValue("PHONE")
public class PhoneContactItem extends Contact {
    private String number;

    public PhoneContactItem(String number) {
        validate(number != null && !number.isBlank(), "Invalid number!");

        this.number = number;
    }

    private PhoneContactItem() {
    }


    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneContactItem that = (PhoneContactItem) o;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "PhoneContactItem{" +
                "number='" + number + '\'' +
                "} " + super.toString();
    }
}
