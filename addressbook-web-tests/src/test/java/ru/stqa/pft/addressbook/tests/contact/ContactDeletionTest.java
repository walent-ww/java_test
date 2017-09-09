package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;
import java.util.Set;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void beforeMethod(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
            app.goTo().createContact();
            app.contact().create(new ContactData().
                    withFname("Firstname").withMname("MiddleName").withLname("LastName").withPhone1("777 334 52 31").withEmail1("temp@mail.com"));
        }
    }

    @Test
    public void testContactDeletion(){
        // кол-во контактов до удаления
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        // кол-во контактов после удаления
        Set<ContactData> after = app.contact().all();
        // проверям, что стало на 1 контакт меньше
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(after, before);

    }


}
