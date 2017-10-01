package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by user on 01.10.17.
 */
public class LoginHelper extends BaseHelper{
    private final ApplicationManager app;
    private WebDriver wd;

    public LoginHelper(ApplicationManager app) throws IOException {
        this.app = app;
        //app.init();
        wd = app.getDriver();
    }

    public void log(String login, String passw){
        wd.get(app.getProperty("webUrl"));
        //wd.findElement(By.id("username")).sendKeys("df");
        type(By.id("username"), "dfgds");

    }
}
