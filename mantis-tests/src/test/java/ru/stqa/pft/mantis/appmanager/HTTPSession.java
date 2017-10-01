package ru.stqa.pft.mantis.appmanager;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

/**
 * Created by user on 30.09.17.
 */
public class HTTPSession {
    private CloseableHttpClient httpClient;
    private ApplicationManager app;

    public HTTPSession(ApplicationManager app){
        this.app = app;
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }
}
