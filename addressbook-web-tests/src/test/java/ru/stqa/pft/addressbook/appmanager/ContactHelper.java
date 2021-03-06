package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.*;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactCreation(ContactData contactData, boolean gr) {
        type(By.name("firstname"), contactData.getFname());
        type(By.name("middlename"), contactData.getMname());
        type(By.name("address"), contactData.getAddress1());
        type(By.name("lastname"), contactData.getLname());
        type(By.name("email"), contactData.getEmail1());
        //attach(By.name("photo"),contactData.getPhoto());

        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());

        if (gr) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        }
    }

    public void modify(ContactData contact) {
        gotoModificationContactById(contact.getId());
        fillContactCreation(contact, false);
        submitContactModification();
        returnHomePage();
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void gotoModificationContact(int i) {
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[8]/a/img"));
    }

    public void gotoModificationContactById(int id) {
        wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id))).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deletionContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void delete(int index) {
        click(index);
        deletionContact();
    }

    public void delete(ContactData contact) {
        clickById(contact.getId());
        deletionContact();
    }

    public void clickById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void click(int i) {
        wd.findElements(By.name("selected[]")).get(i).click();
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//div[1]/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

    public void create(ContactData contactData, boolean gr) {
        fillContactCreation(contactData, gr);
        submitContactCreation();
        returnHomePage();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            // достаем из таблицы по номеру
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lname = cells.get(1).getText();
            String fname = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFname(fname).withLname(lname));
        }

        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            // достаем из таблицы по номеру
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lname = cells.get(1).getText();
            String fname = cells.get(2).getText();
            String address1 = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFname(fname).withLname(lname).
                    withAllPhones(allPhones).withAddress1(address1).withAllEmails(allEmails));
        }

        return contacts;
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void sortById(List<ContactData> before, List<ContactData> after) {
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        gotoModificationContactById(contact.getId());
        String fname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String address1 = wd.findElement(By.name("address")).getText();
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        return new ContactData().withId(contact.getId()).withFname(fname).withLname(lname).withHomePhone(homePhone).
                withMobilePhone(mobilePhone).withWorkPhone(workPhone).withAddress1(address1).withEmail1(email1).withEmail2(email2).withEmail3(email3);

    }

    public void addGroup() {
        wd.findElement(By.name("add")).click();
    }

    public void selectGroupListAddTo(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    }

    // добавление контакту группы
    public void contactAddGroup(ContactData editedContact, GroupData group) throws InterruptedException {
        clickById(editedContact.getId());
        selectGroupListAddTo(group);
        addGroup();
        System.out.println("added");
        Thread.sleep(2000);
    }

    //передаем контакт, чтоб найти группу, в которой его нет
    public GroupData groupForContact(ContactData contact, Groups groups) {
        for (GroupData g : groups) {
            if (!contact.getGroups().contains(g)) {
                return g;
            }

        }
        return null;
    }

    public ContactData searchContactForGroup(Contacts contacts, Groups groups) {
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() < groups.size())
                return contact;
        }
        return null;
    }

    public void deleteFromGroup(ContactData contact, GroupData group) {
        selectGroupList(group);
        clickById(contact.getId());
        removeFromGroup(group);
    }

    private void removeFromGroup(GroupData group) {
        Assert.assertEquals(wd.findElement(By.name("remove")).getAttribute("value"), "Remove from \"" + group.getName() + "\"");
        wd.findElement(By.name("remove")).click();
    }

    private void selectGroupList(GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }

    // Группа, из которой удалим контакт
    public GroupData groupForContactDel(Contacts contacts, Groups groups) {
        for (GroupData g : groups) {
            for (ContactData c : contacts) {
                if (c.getGroups().contains(g)) {
                    return g;
                }

            }
        }
        return null;
    }

    // первый попавшийся контакт, которые есть в этой группе
    public ContactData contactForDel(Contacts contacts, GroupData group) {
        for (ContactData c : contacts){
            if (c.getGroups().contains(group))
                return c;
        } return null;
    }
}
