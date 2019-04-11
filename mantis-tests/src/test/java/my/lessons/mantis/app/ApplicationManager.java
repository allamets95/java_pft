package my.lessons.mantis.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private HttpSession registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private DbHelper dbHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private UsersHelper usersHelper;
    private JamesHelper jamesHelper;


    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();
    }


    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


    }


    public void stop() {
        if (wd != null) {
            wd.quit();
        }

    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public HttpSession newSession() {
        if (registrationHelper == null) {
            registrationHelper = new HttpSession(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public NavigationHelper navigateTo() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public DbHelper db() {
        if (dbHelper == null) {
            dbHelper = new DbHelper();
        }
        return dbHelper;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public UsersHelper users() {
        if (usersHelper == null) {
            usersHelper = new UsersHelper(this);
        }
        return usersHelper;
    }

    public RegistrationHelper registration() {
        return new RegistrationHelper(this);
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.FIREFOX))
                wd = new FirefoxDriver();
            else if (browser.equals(BrowserType.CHROME))
                wd = new ChromeDriver();
            else if (browser.equals(BrowserType.IE))
                wd = new InternetExplorerDriver();

            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));

        }
        return wd;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james(){
        if (jamesHelper==null){
            jamesHelper=new JamesHelper(this);

        }
        return jamesHelper;
    }


}