package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 01.10.17.
 */
public class LoginHelper extends BaseHelper{

    public LoginHelper(ApplicationManager app) {
        super(app);
    }

    // Login
    public void log(String login, String passw) throws InterruptedException {
        wd.get(app.getProperty("webUrl"));
        type(By.name("username"), login);
        click(By.cssSelector("input[value='Login']"));
        Thread.sleep(1000);
        type(By.name("password"), passw);
        click(By.cssSelector("input[value='Login']"));
        Thread.sleep(100);
    }

    // enter new passw
    public void finish(String confLink, String pass1) throws InterruptedException {
        wd.get(confLink);
        Thread.sleep(1000);
        type(By.name("password"), pass1);
        type(By.name("password_confirm"), pass1);
       // click(By.cssSelector("input[value='Update User']"));
        click(By.cssSelector("input[value='Update User']"));
        Thread.sleep(1500);
    }

    // find confirmation Link
    public String findConfLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        System.out.println(mailMessage.text);
        return regex.getText(mailMessage.text);
    }

    // generate Password
    public String generatePassw(){
        String newPassw = RandomStringUtils.randomAlphabetic(8);
        System.out.println(newPassw);
        return newPassw;

    }

    public String confirmLink(UserData user) throws MessagingException, IOException {
        String email = user.getEmail();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
        return app.login().findConfLink(mailMessages, email);
    }

}
