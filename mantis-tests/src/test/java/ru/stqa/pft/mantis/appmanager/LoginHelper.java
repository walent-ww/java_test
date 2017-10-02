package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

/**
 * Created by user on 01.10.17.
 */
public class LoginHelper extends BaseHelper{
    //private final ApplicationManager app;

    public LoginHelper(ApplicationManager app) {
        super(app);
    }


    public void log(String login, String passw) throws InterruptedException {
        wd.get(app.getProperty("webUrl"));
        type(By.id("username"), login);
        click(By.cssSelector("input[value='Login']"));
        Thread.sleep(1000);
        type(By.id("password"), passw);
        click(By.cssSelector("input[value='Login']"));
        Thread.sleep(100);
        assertThat(wd.findElement(By.className("user-info")).getText(),equalTo(login));
    }

    public void finish(String confLink, String pass1) throws InterruptedException {
        wd.get(confLink);
        type(By.name("password"), pass1);
        type(By.name("password_confirm"), pass1);
       // click(By.cssSelector("input[value='Update User']"));
        click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button"));
        Thread.sleep(1500);
        assertThat(wd.findElement(By.className("user-info")).getText(),equalTo("user1"));
        
    }

    public void logout() {
    }
}
