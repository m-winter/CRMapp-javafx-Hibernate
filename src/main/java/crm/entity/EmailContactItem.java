package crm.entity;


import crm.util.ArgumentValidator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

import static crm.util.ArgumentValidator.*;

@Entity
@DiscriminatorValue("EMAIL")
public class EmailContactItem extends Contact {
    private String email;

    public EmailContactItem(String email) {
        validate(email != null && !email.isBlank(), "Invalid email!");
        this.email = email;
    }

    private EmailContactItem() {
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailContactItem that = (EmailContactItem) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "EmailContactItem{" +
                "email='" + email + '\'' +
                "} " + super.toString();
    }
}

