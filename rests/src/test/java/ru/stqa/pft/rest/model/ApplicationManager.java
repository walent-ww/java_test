package ru.stqa.pft.rest.model;

import java.net.MalformedURLException;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    private RestHelper restHelper;

    public ApplicationManager() {
        properties = new Properties();

    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RestHelper rest() throws MalformedURLException {
        if (restHelper == null){
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }

}
