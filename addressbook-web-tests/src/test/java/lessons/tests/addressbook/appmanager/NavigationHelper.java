package lessons.tests.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper extends HelperBase {
    public ChromeDriver wd;

    public NavigationHelper(ChromeDriver wd) {

        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void returnToHomePage() {
      click(By.linkText("home page"));
    }

    public void gotoAddContact() {
     click(By.linkText("add new"));
    }
}
