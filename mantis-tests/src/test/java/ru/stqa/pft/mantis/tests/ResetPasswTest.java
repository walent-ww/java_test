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
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

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
        // какой-нить юзер не админ
        UserData user = app.db().users().iterator().next();

        app.login().log(app.getProperty("webLogin"), app.getProperty("webPassw"));
        app.goTo().manageUser();
        app.goTo().clickUser(user.getName());
        app.goTo().resetPassw();
        String email = user.getEmail();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
        String confLink = findConfLink(mailMessages, email);
        app.login().finish(confLink, "pass1");
        Assert.assertTrue(app.newSession().login(user.getName(), "pass1"));

    }

    private String findConfLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        System.out.println(mailMessage.text);
        return regex.getText(mailMessage.text);
    }
}
