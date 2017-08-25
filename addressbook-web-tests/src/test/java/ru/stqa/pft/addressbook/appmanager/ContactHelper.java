package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

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

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void gotoModificationContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deletionContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void clickContact() {
        click(By.xpath("//div[1]/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }
}
