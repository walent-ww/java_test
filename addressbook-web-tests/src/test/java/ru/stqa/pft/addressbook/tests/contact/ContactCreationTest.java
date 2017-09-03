package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {

        // кол-во контактов до создания нового
        int beforeCount = app.getContactHelper().getContactCount();
        // коллекция контактов до
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().gotoContactCreation();
        ContactData contact = new ContactData("Firstname", "MiddleName", "LastName", "777 334 52 31", "temp@mail.com");
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().returnHomePage();

        // кол-во контактов после создания нового
        int afterCount = app.getContactHelper().getContactCount();
        // коллекция контактов после
        List<ContactData> after = app.getContactHelper().getContactList();
        // проверяем, что контактов стало больше на 1
        Assert.assertEquals(afterCount, beforeCount + 1);
        Assert.assertEquals(after.size(), before.size() + 1);

        // вычисление ID (max) для нового контакта
        int max = after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId();
        contact.setId(max);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
