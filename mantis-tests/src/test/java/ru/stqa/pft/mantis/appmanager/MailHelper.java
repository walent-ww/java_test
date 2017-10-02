package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user on 01.10.17.
 */
public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app){
        this.app  = app;
        wiser = new Wiser();

    }

    public List<MailMessage> waitForMail(int count, long timeout) throws MessagingException, IOException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout) {
            if (wiser.getMessages().size() >= count) {
                System.out.println(wiser.getMessages());
                return wiser.getMessages().stream().map((w) -> toModelMail(w)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(wiser.getMessages());
        throw new Error("No");
    }

    public static MailMessage toModelMail(WiserMessage m) {
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void start() {
        wiser.setPort(1025);
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }
}
