package ru.stqa.pft.addressbook.tests.contact;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactCreation();
            app.getContactHelper().createContact(new ContactData("Firstname_update", "MiddleName_update", "LastName_update", "777 334 52 31_update", "temp@mail.com_update"));
            app.getNavigationHelper().returnHomePage();
        }
        app.getContactHelper().clickContact();
        app.getContactHelper().gotoModificationContact();
        app.getContactHelper().fillContactCreation(new ContactData("Firstname_update", "MiddleName_update", "LastName_update", "777 334 52 31_update", "temp@mail.com_update"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnHomePage();

    }

}
