package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by user on 07.10.17.
 */
public class SoapHelper {

    private final ApplicationManager app;

    public SoapHelper (ApplicationManager app) throws MalformedURLException, ServiceException {
        this.app = app;
    }

   public IssueData getIsuueById(int id) throws MalformedURLException, ServiceException, RemoteException {
       MantisConnectPortType mc = mantisConnect();
       IssueData issueData = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(id));
       return issueData;
   }

    public MantisConnectPortType mantisConnect() throws MalformedURLException, ServiceException {
    return new MantisConnectLocator().
            getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));
    }


    public Set<Issue> issues() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = mantisConnect();
        IssueData[] issues = mc.mc_filter_get_issues("administrator", "root", BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(-1));
        return Arrays.stream(issues).map((i) -> new Issue().withId(i.getId().intValue()).withSum(i.getSummary())
                .withStatus(i.getStatus().toString())).collect(Collectors.toSet());

    }
}
