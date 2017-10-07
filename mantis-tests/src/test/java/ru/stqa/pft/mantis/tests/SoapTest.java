package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by user on 07.10.17.
 */
public class SoapTest extends TestBase{

    @Test
    public void testNoFixed() throws MalformedURLException, RemoteException, ServiceException {
        int idTask = 1;
        skipIfNotFixed(idTask);
        app.soap().mantisConnect();
        System.out.println(isIssueOpen(idTask));
    }

    @Test
    public void testFixed() throws MalformedURLException, RemoteException, ServiceException {
        int idTask = 2;
        skipIfNotFixed(idTask);
        app.soap().mantisConnect();
        System.out.println(isIssueOpen(idTask));
    }

    @Test
    public void test() throws MalformedURLException, RemoteException, ServiceException {
        app.soap().mantisConnect();
        Issue issue = app.soap().issues().iterator().next();
        System.out.println(issue.getId());
        skipIfNotFixed(issue.getId());
        app.soap().mantisConnect();
        System.out.println(isIssueOpen(issue.getId()));
    }
}
