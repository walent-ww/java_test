package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase{

    @BeforeMethod
    public void beforeMethod(){
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactCreation();
            app.getContactHelper().createContact(new ContactData("Firstname", "MiddleName", "LastName", "777 334 52 31", "temp@mail.com"));
            app.getContactHelper().returnHomePage();
        }
    }


    @Test
    public void testContactModification(){
        // кол-во контактов до модификации
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().clickContact(index);
        ContactData contact = new ContactData(before.get(index).getId(),"Firstname_update", "MiddleName_update", "LastName_update", "777 334 52 31_update", "temp@mail.com_update");
        app.getContactHelper().modifyContact(index, contact);
        // кол-во контактов после модификации
        List<ContactData> after = app.getContactHelper().getContactList();
        // проверяем, что кол-ва равны
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        app.getContactHelper().sortById(before, after);
        Assert.assertEquals(after, before);

    }

}
