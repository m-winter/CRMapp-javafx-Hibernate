package crm.entity;

import javax.persistence.*;
import java.time.LocalDate;
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

    @OneToMany(cascade = CascadeType.ALL)  //if there is smth that includes Address save it
    @JoinColumn(name = "customer_id")
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL)
    private PremiumStatus premiumStatus;

    @OneToOne(cascade = CascadeType.ALL)
    private VerificationStatus verificationStatus;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactList contactList;

    public Customer() {
        this.id = UUID.randomUUID();
        this.addresses = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void addAddress(Address address) {  //avoid setters, use functions
        if (!addresses.contains(address)) {
            addresses.add(address);
        }
    }

    public void addPremiumStatus(PremiumStatus premiumStatus1) {
        this.premiumStatus =new PremiumStatus(premiumStatus1.isActive(), premiumStatus1.getExpireAt(), premiumStatus1.getPremiumType());

    }

    public void addVerificationStatus(VerificationStatus verificationStatus1){
        this.verificationStatus = new VerificationStatus(verificationStatus1.isVerified(), verificationStatus1.getVerifiedAt(), verificationStatus1.getVerifiedBy());
    }

    public void addContactList(ContactList contactList){
        this.contactList = contactList;
    }


    public List<Address> getAddresses() {
        return new ArrayList<>(addresses); //give copy of list  to avoid problems
    }

    public ContactList getContactList() {
        return contactList;
    }

    public PremiumStatus getPremiumStatus() {
        return premiumStatus;
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", addresses=" + addresses +
                ", premiumStatus=" + premiumStatus +
                ", verificationStatus=" + verificationStatus +
                ", contactList=" + contactList +
                '}';
    }
}
