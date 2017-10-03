package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

/**
 * Created by user on 01.10.17.
 */
public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Users users(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData where id != 1" ).list(); //id=1 только у админа
        for ( UserData user : result) {
            System.out.println(user);
        }
        session.getTransaction().commit();
        session.close();
        return new Users(result);
    }
}
