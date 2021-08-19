package crm.service;

import java.util.Objects;

import static java.util.Objects.*;

public class RegisterPersonForm {
    private final String firstName;
    private final String lastName;
    private final String ssNumber;

    public RegisterPersonForm(String firstName, String lastName, String ssNumber) {
        this.firstName = requireNonNull(firstName);
        this.lastName = requireNonNull(lastName);
        this.ssNumber = requireNonNull(ssNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSsNumber() {
        return ssNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterPersonForm that = (RegisterPersonForm) o;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName) && ssNumber.equals(that.ssNumber);
    }

    @Override
    public int hashCode() {
        return hash(firstName, lastName, ssNumber);
    }


    @Override
    public String toString() {
        return "RegisterPersonForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssNumber='" + ssNumber + '\'' +
                '}';
    }
}
