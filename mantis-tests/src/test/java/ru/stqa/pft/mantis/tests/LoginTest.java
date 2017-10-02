package ru.stqa.pft.mantis.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by user on 02.10.17.
 */
public class LoginTest extends TestBase {

    @Test
    public void test1() throws IOException {
        HttpSession session = app.newSession();
        session.login("administrator", "root");
        assertTrue(session.login("administrator", "root"));

    }

}