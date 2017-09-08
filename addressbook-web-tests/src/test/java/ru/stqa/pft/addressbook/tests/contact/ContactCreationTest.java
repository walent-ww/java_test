package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {

        // кол-во контактов до создания нового
        int beforeCount = app.contact().getContactCount();
        // коллекция контактов до
        List<ContactData> before = app.contact().list();

        app.goTo().createContact();
        ContactData contact = new ContactData("Firstname", "MiddleName", "LastName", "777 334 52 31", "temp@mail.com");
        app.contact().create(contact);

        // кол-во контактов после создания нового
        int afterCount = app.contact().getContactCount();
        // коллекция контактов после
        List<ContactData> after = app.contact().list();
        // проверяем, что контактов стало больше на 1
        Assert.assertEquals(afterCount, beforeCount + 1);
        Assert.assertEquals(after.size(), before.size() + 1);

        // вычисление ID (max) для нового контакта
        int max = after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId();
        contact.setId(max);
        before.add(contact);

        app.contact().sortById(before, after);
        Assert.assertEquals(after, before);
    }

}
