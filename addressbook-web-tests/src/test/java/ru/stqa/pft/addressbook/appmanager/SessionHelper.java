package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends BaseHelper {

      public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String passw) {
        type(By.name("user"), username);
        type(By.name("pass"), passw);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
