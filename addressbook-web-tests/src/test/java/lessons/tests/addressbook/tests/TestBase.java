package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.appmanager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeSuite;


public class TestBase {


    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }


}
