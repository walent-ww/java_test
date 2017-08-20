package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper{

    public ContactHelper(FirefoxDriver wd) {
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

}
