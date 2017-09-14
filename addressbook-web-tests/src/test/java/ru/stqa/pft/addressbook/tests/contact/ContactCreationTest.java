package ru.stqa.pft.addressbook.tests.contact;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {

        // множество контактов до
        Contacts before = app.contact().all();
        app.goTo().createContact();
        File photo = new File("src/test/resources/kraken.jpg");
        ContactData contact = new ContactData().
                withFname("Firstname").withMname("MiddleName").withLname("LastName").withPhone1("777 334 52 31").
                withEmail1("temp@mail.com").withPhoto(photo);
        app.contact().create(contact);
        // множество контактов после
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        // проверяем, что контактов стало больше на 1
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
