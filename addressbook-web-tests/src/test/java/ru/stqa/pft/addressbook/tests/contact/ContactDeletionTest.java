package ru.stqa.pft.addressbook.tests.contact;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactDeletionTest extends TestBase {
    @Test
    public void testContactDeletion(){
        app.getContactHelper().clickContact();
        app.getContactHelper().deletionContact();
        app.getNavigationHelper().returnHomePage();
    }


}
