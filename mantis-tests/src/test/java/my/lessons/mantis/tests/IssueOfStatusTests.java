package my.lessons.mantis.tests;

import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class IssueOfStatusTests extends TestBase
{
    @Test
    public void testIssueOfStatus() throws RemoteException, ServiceException, MalformedURLException {

        int issueId = 1;
        skipIfNotFixed(issueId);
        System.out.println(String.format("Issue#%s was fixed - enjoy the test!", issueId));
    }


}
