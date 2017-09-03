package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactCreation();
            app.getContactHelper().createContact(new ContactData("Firstname_update", "MiddleName_update", "LastName_update", "777 334 52 31_update", "temp@mail.com_update"));
            app.getNavigationHelper().returnHomePage();
        }
        // кол-во контактов до модификации
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().clickContact(before.size() - 1);
        app.getContactHelper().gotoModificationContact();
        app.getContactHelper().fillContactCreation(new ContactData("Firstname_update", "MiddleName_update", "LastName_update", "777 334 52 31_update", "temp@mail.com_update"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnHomePage();
        // кол-во контактов после модификации
        List<ContactData> after = app.getContactHelper().getContactList();
        // проверяем, что кол-ва равны
        Assert.assertEquals(after.size(), before.size());

    }

}
