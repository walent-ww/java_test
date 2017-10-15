package ru.stqa.pft.addressbook.tests.contact;

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
public class ContactAddGroupTest extends TestBase {

    @BeforeMethod
    public void beforeMethodGroup(){
        app.goTo().groupPage();
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("group1"));
        }
    }

    @BeforeMethod
    public void beforeMethodContact() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0){
            app.goTo().createContact();
            app.contact().create(new ContactData().
                    withFname("Firstname").withMname("MiddleName").withLname("LastName").
                    withPhone1("777 334 52 31").withEmail1("temp@mail.com"), false);
        }
    }

    @BeforeMethod(dependsOnMethods = "beforeMethodContact",
            description = "Проверка, что есть контакт, которому можно добавить группу, иначе создаем контакт")
    public void beforeMethodContactForGroup() {
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
                    withPhone1("777 334 52 31").withEmail1("temp@mail.com"), false);
        }
    }


    @Test
    public void testContactAddGroup() throws InterruptedException {
        Thread.sleep(1000);
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

        // берем контакт, у которого не все группы
        ContactData editedContact = app.contact().searchContactForGroup(contacts, groups);
        System.out.println(editedContact);

        // выбираем группу, которой еще нет у контакта
        GroupData group = app.contact().groupForContact(editedContact, groups);
        System.out.println(group);

        // достаём из БД изменяемый контакт
        ContactData contactBefore = app.db().contact(editedContact.getId());
        System.out.println("before" + contactBefore.getGroups());
        app.goTo().homePage();
        app.contact().contactAddGroup(editedContact, group);

        // достаём из БД измененный контакт
        ContactData contactAfter = app.db().contact(editedContact.getId());
        Groups groupAfter = contactAfter.getGroups().without(group);
        System.out.println("after" + contactAfter.getGroups().without(group));
        assertThat(contactBefore.getGroups(),equalTo(groupAfter));

    }

}
