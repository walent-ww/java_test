package ru.stqa.pft.mantis.appmanager;

/**
 * Created by user on 02.10.17.
 */

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
    private CloseableHttpClient httpclient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    // mantis 1.2.19
    public boolean login(String username, String password) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("webUrl") + "login.php");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpclient.execute(post);
        String body = geTextFrom(response);
        return body.contains(String.format("<span class=\"italic\">%s</span>", username));
    }

    private String geTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    // mantis 2.4.2
    // пока не осилила
   /* public boolean login2(String username, String password) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("webUrl") + "login_page.php");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("secure_session", "off"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        httpclient.execute(post);

        HttpPost post2 = new HttpPost("http://localhost/mantisbt-2.4.2/login_password_page.php");
        List<NameValuePair> params2 = new ArrayList<NameValuePair>();
        params2.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("secure_session", "off"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post2.setEntity(new UrlEncodedFormEntity(params2));
        CloseableHttpResponse response2 = httpclient.execute(post2);

        String body = geTextFrom(response2);
        System.out.println(body);
        return body.contains(String.format("<a href=\"/mantisbt-2.4.2/account_page.php\">%s</a>", username));
    }*/


}