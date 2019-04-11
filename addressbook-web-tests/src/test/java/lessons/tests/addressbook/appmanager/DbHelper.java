package lessons.tests.addressbook.appmanager;

import lessons.tests.addressbook.model.ContactData;
import lessons.tests.addressbook.model.Contacts;
import lessons.tests.addressbook.model.GroupData;
import lessons.tests.addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        for (GroupData group : result) {
            System.out.println(group.getContacts());
        }
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        for (ContactData contact : result) {
            System.out.println(contact.getGroups());
        }
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public Contacts contactByIdInDB(int contactId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where id ='" + contactId + "'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }
}