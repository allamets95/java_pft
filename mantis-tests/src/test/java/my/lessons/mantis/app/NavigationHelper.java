package my.lessons.mantis.app;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;

public class NavigationHelper extends HelperBase {


    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUsersPage() {
        if (isElementPresent(By.cssSelector("input[value='Create New Account']")) &&
                isElementPresent(By.cssSelector("input[value='Manage User']"))) {
            String text = wd.findElement(By.xpath("html/body/table[3]")).getText();
            if (text.contains("Manage Accounts [")) {
                return;
            } else {
                throw new ElementNotVisibleException("Title is not visible");
            }

        }
        click(By.linkText("Manage"));
        click(By.linkText("Manage Users"));

    }

    public void reactivation(String reactivationURL) {
        wd.get(reactivationURL);
    }
}