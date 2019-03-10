package lessons.tests.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class NavigationHelper extends HelperBase {


    public NavigationHelper(WebDriver wd) {

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

    public void gotoHomePage() {
        click(By.linkText("home"));
    }

}
