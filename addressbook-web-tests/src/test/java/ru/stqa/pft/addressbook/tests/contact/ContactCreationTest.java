package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {

        // множество контактов до
        Set<ContactData> before = app.contact().all();

        app.goTo().createContact();
        ContactData contact = new ContactData().
                withFname("Firstname").withMname("MiddleName").withLname("LastName").withPhone1("777 334 52 31").withEmail1("temp@mail.com");
        app.contact().create(contact);

        // множество контактов после
        Set<ContactData> after = app.contact().all();
        // проверяем, что контактов стало больше на 1
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(after, before);
    }

}
