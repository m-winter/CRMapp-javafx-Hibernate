package crm.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customers") // table name
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //set everything in one table - join tables
@DiscriminatorColumn(name = "CUSTOMERTYPE")//generate column which describes type of customer
public abstract class Customer {
    @Id
    private UUID id;

    @OneToMany
    private List<Address> addresses;

    public Customer() {
    this.id = UUID.randomUUID();
    this.addresses = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void addAddress(Address address){  //avoid setters, use functions
        if (!addresses.contains(address)){
            addresses.add(address);
        }
    }

    public List<Address> getAddresses(){
        return new ArrayList<>(addresses); //give copy to avoid problems
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
