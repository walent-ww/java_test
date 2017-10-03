package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private MailHelper mailHelper;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }


    public void stop() {
        if (wd != null)
            wd.quit();
    }


    public WebDriver getDriver() {
     if (wd == null){
         if (browser.equals(BrowserType.FIREFOX)){
             wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("/home/user/Документы/firefox/firefox"));
         } else if (browser.equals(BrowserType.CHROME)){
             wd = new ChromeDriver();
         }

         wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
         wd.get(properties.getProperty("webUrl"));
     }
        return wd;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public LoginHelper login() throws IOException {
        return new LoginHelper(this);
    }

    public NavigationHelper goTo() {
        return new NavigationHelper(this);
    }

    public MailHelper mail(){
        if (mailHelper == null)
            mailHelper = new MailHelper(this);
        return mailHelper;
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public DbHelper db() {
        if (dbHelper == null) {
            dbHelper = new DbHelper();
        }
        return dbHelper;
    }
}
