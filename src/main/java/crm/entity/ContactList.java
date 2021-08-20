package crm.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "contactList")
public class ContactList {
    @Id
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_list_id")
    private List<Contact> contacts;

    public ContactList() {
        this.id = UUID.randomUUID();
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact){

            this.contacts.add(contact);
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactList that = (ContactList) o;
        return id.equals(that.id) && contacts.equals(that.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contacts);
    }

    @Override
    public String toString() {
        return "ContactList{" +
                "id=" + id +
                ", contacts=" + contacts +
                '}';
    }
}
