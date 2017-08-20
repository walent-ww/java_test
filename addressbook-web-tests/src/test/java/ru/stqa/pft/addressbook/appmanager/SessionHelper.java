package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper {

    private FirefoxDriver wd;

    public SessionHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void login(String username, String passw) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(passw);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }
}
