package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by user on 23.09.17.
 */
public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeClass
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
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        for ( GroupData group : result) {
            System.out.println(group);
            System.out.println(group.getContacts());
        }
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testHbConnectionContacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        for ( ContactData contact : result) {
            System.out.println(contact);
            System.out.println(contact.getGroups());
        }
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testHbConnectionContact(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00' and id = 141" ).list();
        for ( ContactData contact : result) {
            System.out.println(contact);
            System.out.println(contact.getGroups());
        }
        session.getTransaction().commit();
        session.close();
    }
}
