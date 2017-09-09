package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.*;

public class ContactHelper extends BaseHelper{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactCreation(ContactData contactData) {
        type(By.name("firstname"),contactData.getFname());
        type(By.name("middlename"),contactData.getMname());
        type(By.name("lastname"),contactData.getLname());
        type(By.name("home"),contactData.getPhone1());
        type(By.name("email"),contactData.getEmail1());
    }

    public void modify(ContactData contact) {
        gotoModificationContactById(contact.getId());
        fillContactCreation(contact);
        submitContactModification();
        returnHomePage();

    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void gotoModificationContact(int i) {
        click(By.xpath("//table[@id='maintable']/tbody/tr["+ i + "]/td[8]/a/img"));
    }

    public void gotoModificationContactById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
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

    private void clickById(int id) {
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

    public void create(ContactData contactData) {
        fillContactCreation(contactData);
        submitContactCreation();
        returnHomePage();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
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
        for (WebElement element : elements){
            // достаем из таблицы по номеру
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lname = cells.get(1).getText();
            String fname = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFname(fname).withLname(lname));
        }

        return contacts;
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void sortById (List<ContactData> before, List<ContactData> after){
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
    }

}
