package ru.stqa.pft.addressbook.tests.contact;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {

        // множество контактов до
        Contacts before = app.contact().all();
        app.goTo().createContact();
        ContactData contact = new ContactData().
                withFname("Firstname").withMname("MiddleName").withLname("LastName").withPhone1("777 334 52 31").withEmail1("temp@mail.com");
        app.contact().create(contact);
        // множество контактов после
        Contacts after = app.contact().all();
        // проверяем, что контактов стало больше на 1
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
