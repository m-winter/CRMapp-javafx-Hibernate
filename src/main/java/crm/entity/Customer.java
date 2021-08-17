package crm.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customers") // table name
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //set everything in one table - join tables
@DiscriminatorColumn(name = "CUSTOMERTYPE")//generate column which describes type of customer
public abstract class Customer {
    @Id
    private UUID id;

    public Customer() {
    this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
