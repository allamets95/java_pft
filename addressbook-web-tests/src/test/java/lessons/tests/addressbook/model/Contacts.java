package lessons.tests.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {
    private Set<ContactData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }

    public Contacts(Collection<ContactData> contacts) {
        this.delegate = new HashSet<ContactData>(contacts);
    }
    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAddedc(ContactData contactData) {
        Contacts contacts = new Contacts(this);
        contacts.add(contactData);
        return contacts;
    }

    public Contacts withoutc(ContactData contactData) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contactData);
        return contacts;
    }
}
