package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactCreation();
            app.getContactHelper().createContact(new ContactData("Firstname", "MiddleName", "LastName", "777 334 52 31", "temp@mail.com"));
            app.getNavigationHelper().returnHomePage();
        }
        // кол-во контактов до модификации
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().clickContact(before.size() - 1);
        System.out.println(before.size());
        app.getContactHelper().gotoModificationContact(before.size() + 1); // тк нумерация с 1 и 1 поле - шапка
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Firstname_update", "MiddleName_update", "LastName_update", "777 334 52 31_update", "temp@mail.com_update");
        app.getContactHelper().fillContactCreation(contact);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnHomePage();
        // кол-во контактов после модификации
        List<ContactData> after = app.getContactHelper().getContactList();
        // проверяем, что кол-ва равны
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        app.getContactHelper().sortById(before, after);
        Assert.assertEquals(after, before);

    }

}
