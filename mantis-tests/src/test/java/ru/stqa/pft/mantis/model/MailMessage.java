package ru.stqa.pft.mantis.model;

/**
 * Created by user on 01.10.17.
 */
public class MailMessage {

    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
