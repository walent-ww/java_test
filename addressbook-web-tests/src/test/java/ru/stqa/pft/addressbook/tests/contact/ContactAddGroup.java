package ru.stqa.pft.addressbook.tests.contact;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
/**
 * Created by user on 24.09.17.
 */
public class ContactAddGroup extends TestBase {

    @BeforeMethod
    public void beforeMethodGroup(){
        app.goTo().groupPage();
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("group1"));
        }
    }

    @BeforeMethod
    public void beforeMethodContact(){
        app.goTo().homePage();
        if (app.db().contacts().size() == 0){
            app.goTo().createContact();
            app.contact().create(new ContactData().
                    withFname("Firstname").withMname("MiddleName").withLname("LastName").
                    withPhone1("777 334 52 31").withEmail1("temp@mail.com"));
        }
    }

    @BeforeMethod(dependsOnMethods = "beforeMethodContact",
            description = "Проверка, что есть контакт, которому можно добавить группу, иначе создаем контакт")
    public void beforeMethodContactForGroup(){
        app.goTo().homePage();
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        int i = 0;
        for (ContactData contact : contacts){
            if (contact.getGroups().size() == groups.size())
                i++;
        }
        if (i == contacts.size()){
            app.goTo().createContact();
            app.contact().create(new ContactData().
                    withFname("Firstname").withMname("MiddleName").withLname("LastName").
                    withPhone1("777 334 52 31").withEmail1("temp@mail.com"));
        }
    }


    @Test
    public void testContactAddGroup() throws InterruptedException {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

        // берем контакт, у которого не все группы
        ContactData editedContact = app.contact().searchContactForGroup(contacts, groups);

        //ContactData editedContact = contacts.iterator().next();
        GroupData group = app.contact().groupForContact(editedContact, groups);

        // достаём из БД изменяемый контакт
        ContactData contactBefore = app.db().contact(editedContact.getId());

        app.goTo().homePage();
        app.contact().contactAddGroup(editedContact, group);

        // достаём из БД измененный контакт
        ContactData contactAfter = app.db().contact(editedContact.getId());

        assertThat(contactBefore.getGroups(),equalTo(contactAfter.getGroups().without(group)));

    }

}
