package crm.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;


import static crm.util.ArgumentValidator.*; //static import ArgumentValidator class
import static java.util.Objects.*;

@Entity
@DiscriminatorValue("PERSON") // set value in coulumn CUSTOMERTYPE
public class Person extends Customer{

    private String firstName;
    private String lastName;

    @Embedded //put ssNumber class with Person in one table (avoid OneToOne)
    private SsNumber ssNumber;// crate separate class & validate data there

    private Person(){}; //constructor for hibernate don`t use!

    public Person(String firstName, String lastName, SsNumber ssNumber) {
        validate(firstName != null || !firstName.isBlank(), "First name is invalid " + firstName);
        validate(lastName != null || !lastName.isBlank(), "First name is invalid " + lastName);

        this.firstName = firstName;
        this.lastName = lastName;
        this.ssNumber = requireNonNull(ssNumber, "SsNumber is null"); //check nonNull
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public SsNumber getSsNumber() {
        return ssNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) && lastName.equals(person.lastName) && ssNumber.equals(person.ssNumber);
    }

    @Override
    public int hashCode() {
        return hash(super.hashCode(), firstName, lastName, ssNumber);
    }
}
