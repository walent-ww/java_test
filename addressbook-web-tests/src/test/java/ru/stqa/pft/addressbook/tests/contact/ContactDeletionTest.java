package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void beforeMethod(){
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactCreation();
            app.getContactHelper().createContact(new ContactData("Firstname_update", "MiddleName_update", "LastName_update", "777 334 52 31_update", "temp@mail.com_update"));
            app.getContactHelper().returnHomePage();
        }
    }

    @Test
    public void testContactDeletion(){
        // кол-во контактов до удаления
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().clickContact(index);
        app.getContactHelper().deletionContact();
        app.getNavigationHelper().gotoHomePage();
        // кол-во контактов после удаления
        List<ContactData> after = app.getContactHelper().getContactList();
        // проверям, что стало на 1 контакт меньше
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        app.getContactHelper().sortById(before, after);
        Assert.assertEquals(after, before);

    }


}
