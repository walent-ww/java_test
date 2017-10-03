package ru.stqa.pft.mantis.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

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
        String newPassw = app.login().generatePassw();

        app.login().log(app.getProperty("webLogin"), app.getProperty("webPassw"));
        app.goTo().modificationUser(user);
        app.goTo().resetPassw();
        String confLink = app.login().confirmLink(user);
        app.login().finish(confLink, newPassw);
        Assert.assertTrue(app.newSession().login(user.getName(), newPassw));

    }

}
