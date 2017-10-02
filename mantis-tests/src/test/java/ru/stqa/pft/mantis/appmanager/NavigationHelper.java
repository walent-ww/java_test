package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }


    public void manageUser() {
        click(By.linkText("Manage"));
        click(By.linkText("Manage Users"));
    }


    public void clickUser(String username) {
         click(By.linkText(username));
    }

    public void resetPassw() {
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
