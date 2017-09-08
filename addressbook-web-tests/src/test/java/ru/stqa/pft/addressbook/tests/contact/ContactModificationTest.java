package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class ContactModificationTest extends TestBase{

    @BeforeMethod
    public void beforeMethod(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
            app.goTo().createContact();
            app.contact().create(new ContactData().
                    withFname("Firstname").withMname("MiddleName").withLname("LastName").
                    withPhone1("777 334 52 31").withEmail1("temp@mail.com"));
        }
    }


    @Test
    public void testContactModification(){
        // кол-во контактов до модификации
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().click(index);
        ContactData contact = new ContactData().
                withId(before.get(index).getId()).
                withFname("Firstname_update").withMname("MiddleName_update").
                withLname("LastName_update").withPhone1("777 334 52 31_update").withEmail1("temp@mail.com_update");
        app.contact().modify(index, contact);
        // кол-во контактов после модификации
        List<ContactData> after = app.contact().list();
        // проверяем, что кол-ва равны
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        app.contact().sortById(before, after);
        Assert.assertEquals(after, before);

    }

}
