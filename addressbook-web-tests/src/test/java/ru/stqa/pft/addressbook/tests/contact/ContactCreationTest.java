package ru.stqa.pft.addressbook.tests.contact;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactCreation();
        app.getContactHelper().createContact(new ContactData("Firstname", "MiddleName", "LastName", "777 334 52 31", "temp@mail.com"));
        app.getNavigationHelper().returnHomePage();
    }

}
