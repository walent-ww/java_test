package ru.stqa.pft.addressbook;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactCreation(new ContactData("Firstname", "MiddleName", "LastName", "777 334 52 31", "temp@mail.com"));
        submitContactCreation();
        returnContactPage();
    }

}
