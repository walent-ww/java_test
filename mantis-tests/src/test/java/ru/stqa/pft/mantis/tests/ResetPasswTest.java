package ru.stqa.pft.mantis.tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.appmanager.BaseHelper;

import java.io.IOException;

/**
 * Created by user on 01.10.17.
 */
public class ResetPasswTest extends TestBase{

    @Test
    public void testResetPassw() throws IOException {
        app.login().log(app.getProperty("webLogin"), app.getProperty("webPassw"));

    }
}
