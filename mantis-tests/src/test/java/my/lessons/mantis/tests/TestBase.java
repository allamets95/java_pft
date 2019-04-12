package my.lessons.mantis.tests;

import my.lessons.mantis.app.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;


public class TestBase {

    //protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
    //protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    org.slf4j.Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php","config_inc.back");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.back", "config_inc.php");
        app.stop();

    }
    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("soap.url")));
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issue = mc.mc_issue_get(app.getProperty("webAdminLogin"), app.getProperty("webAdminPassword"), BigInteger.valueOf(issueId));
        //System.out.println(!issue.getStatus().getName().equals("closed"));
        return !issue.getStatus().getName().equals("closed");
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            logger.info("This test was ignored! Because there is issue with ID " + issueId);
            throw new SkipException("Ignored because of issue " + issueId);

        }

    }

}