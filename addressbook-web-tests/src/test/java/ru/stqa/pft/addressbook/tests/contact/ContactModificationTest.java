package ru.stqa.pft.addressbook.tests.contact;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase{

    @BeforeMethod
    public void beforeMethod()  {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0){
            app.goTo().createContact();
            app.contact().create(new ContactData().
                    withFname("Firstname").withMname("MiddleName").withLname("LastName").
                    withPhone1("777 334 52 31").withEmail1("temp@mail.com"), false);
        }
    }


    @Test
    public void testContactModification(){
        // кол-во контактов до модификации
        Contacts before = app.db().contacts();
        //app.contact().click(index);
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().
                withId(modifiedContact.getId()).
                withFname("Firstname_update").withMname("MiddleName_update").
                withLname("LastName_update").withHomePhone("777 334 52 31_update").withEmail1("temp@mail.com_update");
        app.contact().modify(contact);
        // кол-во контактов после модификации
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        // проверяем, что кол-ва равны
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }

}
