package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by user on 01.10.17.
 */
public class DBTest {
    private SessionFactory sessionFactory;

    @BeforeTest
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }
    @Test
    public void testHbConnectionGroups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Object> result = session.createQuery("from UserData").list();
        /*for ( GroupData group : result) {
            System.out.println(group);
            System.out.println(group.getContacts());
        }*/
        session.getTransaction().commit();
        session.close();
    }
}
