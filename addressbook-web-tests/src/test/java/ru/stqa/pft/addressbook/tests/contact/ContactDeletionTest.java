package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void beforeMethod(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
            app.goTo().createContact();
            app.contact().create(new ContactData("Firstname_update", "MiddleName_update", "LastName_update", "777 334 52 31_update", "temp@mail.com_update"));
        }
    }

    @Test
    public void testContactDeletion(){
        // кол-во контактов до удаления
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().homePage();
        // кол-во контактов после удаления
        List<ContactData> after = app.contact().list();
        // проверям, что стало на 1 контакт меньше
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        app.contact().sortById(before, after);
        Assert.assertEquals(after, before);

    }


}
