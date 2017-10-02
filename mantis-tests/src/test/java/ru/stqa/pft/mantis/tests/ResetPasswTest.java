package ru.stqa.pft.mantis.tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.appmanager.BaseHelper;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 01.10.17.
 */
public class ResetPasswTest extends TestBase{

    @BeforeSuite
    public void startMessage(){
        app.mail().start();
    }
    @AfterSuite
    public void stopMessage(){
        app.mail().stop();
    }

    @Test
    public void testResetPassw() throws IOException, InterruptedException, MessagingException {
        app.login().log(app.getProperty("webLogin"), app.getProperty("webPassw"));
        app.goTo().manageUser();
        Thread.sleep(900);
        app.goTo().clickUser("user1");
        Thread.sleep(100);
        app.goTo().resetPassw();
        //app.login().logout();
        String email = "user1@localhost.localdomain";
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
        String confLink = findConfLink(mailMessages, email);
        app.login().finish(confLink, "pass1");
        Assert.assertTrue(app.newSession().login("user1", "pass1"));

    }

    private String findConfLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        System.out.println(mailMessage.text);
        return regex.getText(mailMessage.text);
    }
}
