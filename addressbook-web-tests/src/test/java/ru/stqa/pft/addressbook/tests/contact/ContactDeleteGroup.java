package ru.stqa.pft.addressbook.tests.contact;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by user on 24.09.17.
 */
public class ContactDeleteGroup extends TestBase{

    @BeforeMethod
    public void beforeMethodGroup(){
        app.goTo().groupPage();
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("group1"));
        }
    }

    @BeforeMethod(dependsOnMethods = "beforeMethodGroup")
    public void beforeMethodContact() throws InterruptedException {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0){
            app.goTo().createContact();
            Groups groups = app.db().groups();
            app.contact().create(new ContactData().
                    withFname("Firstname").withMname("MiddleName").withLname("LastName").
                    withPhone1("777 334 52 31").withEmail1("temp@mail.com").inGroup(groups.iterator().next()), true);
        }
    }

    @BeforeMethod(dependsOnMethods = "beforeMethodContact",
            description = "если нет контакта с группой - создаем такой")
    public void beforeMethodContact2() {
        app.goTo().homePage();
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        int i = 0;
        for (ContactData contact : contacts){
            if (contact.getGroups().size() == 0)
                i++;
        }
        if (i == contacts.size()){
            app.goTo().createContact();
            app.contact().create(new ContactData().
                    withFname("Firstname").withMname("MiddleName").withLname("LastName").
                    withPhone1("777 334 52 31").withEmail1("temp@mail.com").inGroup(groups.iterator().next()), true);
        }
    }

    @Test
    public void testContactDelGroup() {
        app.goTo().homePage();
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

        // выбираем группу, которая есть у хоть одного контакта
        GroupData group = app.contact().groupForContactDel(contacts, groups);
        // выбираем контакт, который в этой группе
        ContactData contact = app.contact().contactForDel(contacts, group);

        // достаём из БД изменяемый контакт
        ContactData contactBefore = app.db().contact(contact.getId());

        app.contact().deleteFromGroup(contact, group);
        ContactData contactAfter = app.db().contact(contact.getId());

        assertThat(contactBefore.getGroups(), equalTo(contactAfter.getGroups().withAdded(group)));

    }



}
