package ru.stqa.pft.addressbook.tests.contact;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 10.09.17.
 */
public class ContactInfoTest extends TestBase {

    @BeforeMethod
    public void beforeMethod() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.goTo().createContact();
            app.contact().create(new ContactData().
                    withFname("Firstname").withMname("MiddleName").withLname("LastName").withPhone1("777 334 52 31").
                    withEmail1("temp@mail.com"), false);
        }
    }

    @Test
    public void  testContactInfo(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEdit = app.contact().infoFromEditForm(contact);
        // телефоны
        assertThat(contact.getAllPhones(), equalTo(mergePhone(contactInfoFromEdit)));
        // адрес
        assertThat(contact.getAddress1(), equalTo(contactInfoFromEdit.getAddress1()));
        // emails
        assertThat(contact.getAllEmails(), equalTo(mergeEmail(contactInfoFromEdit)));
    }

    private String mergePhone(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactInfoTest::cleanedPhone)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                //.map(ContactInfoTest::cleanedPhone)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhone(String phone){
        return phone.replaceAll("[-()\\s]", "");

    }
}
