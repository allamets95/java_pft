package my.lessons.mantis.app;

import my.lessons.mantis.model.UserData;
import my.lessons.mantis.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public Users users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery("from UserData where access_level != 90").list();
        session.getTransaction().commit();
        session.close();

        return new Users(result);
    }
}