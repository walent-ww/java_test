package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTest extends TestBase {
    @Test
    public void testContactDeletion(){
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactCreation();
            app.getContactHelper().createContact(new ContactData("Firstname_update", "MiddleName_update", "LastName_update", "777 334 52 31_update", "temp@mail.com_update"));
            app.getNavigationHelper().returnHomePage();
        }
        // кол-во контактов до удаления
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().clickContact(before.size() - 1);
        app.getContactHelper().deletionContact();
        app.getNavigationHelper().returnHomePage();
        // кол-во контактов после удаления
        List<ContactData> after = app.getContactHelper().getContactList();
        // проверям, что стало на 1 контакт меньше
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        app.getContactHelper().sortById(before, after);
        Assert.assertEquals(after, before);

    }


}
