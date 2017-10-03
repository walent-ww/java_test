package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }


    public void manageUser() throws InterruptedException {
        click(By.linkText("Manage"));
        click(By.linkText("Manage Users"));
        Thread.sleep(900);
    }


    public void clickUser(String username) throws InterruptedException {
        click(By.linkText(username));
        Thread.sleep(100);
    }

    public void resetPassw() {
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
