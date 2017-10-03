package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.mantis.model.UserData;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }


    public void resetPassw() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void modificationUser(UserData user) throws InterruptedException {
        click(By.linkText("Manage"));
        click(By.linkText("Manage Users"));
        Thread.sleep(900);
        click(By.linkText(user.getName()));
        Thread.sleep(100);

    }
}
