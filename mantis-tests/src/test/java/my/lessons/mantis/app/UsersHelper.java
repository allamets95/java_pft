package my.lessons.mantis.app;

import my.lessons.mantis.model.UserData;
import my.lessons.mantis.model.Users;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;



public class UsersHelper extends HelperBase {

    private Users usersCache = null;

    public UsersHelper(ApplicationManager app) {
        super(app);
    }

    private void gotoMyView() {
        click(By.cssSelector("img[alt='MantisBT']"));
    }

    public void resetPasswordForUserById(int id) {
        openContactToModifyById(id);
        resetPassword();
        gotoMyView();
    }

    private void openContactToModifyById(int id) {
        wd.findElement(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id + "']")).click();

    }

    private void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void setNewPassword(String password) {
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }

    public UserData byId(int id) {
        UserData user = new UserData();

        WebElement table = wd.findElement(By.xpath("html/body/table[3]"));
        List<WebElement> rows = cleanUsersTable(table);

        for (WebElement r : rows) {
            String editPageLink = r.findElement(By.tagName("a")).getAttribute("href");
            if (editPageLink
                    .equals(String.format(app.getProperty("web.baseURL") + "manage_user_edit_page.php?user_id=%s", id))) {
                String userName = r.findElement(By.xpath("td[1]")).getText();
                String email = r.findElement(By.xpath("td[3]")).getText();

                user.withUserName(userName).withEmail(email).withId(id);
                break;
            }
        }
        return user;
    }

    public UserData byUserName(String userName) {
        UserData user = new UserData();

        WebElement table = wd.findElement(By.xpath("html/body/table[3]"));
        List<WebElement> rows = cleanUsersTable(table);

        for (WebElement r : rows) {
            if (userName
                    .equals(r.findElement(By.xpath("td[1]")).getText())) {
                int id = Integer.parseInt(r.findElement(By.tagName("a")).getAttribute("href")
                        .replaceAll("(.([^;]*)=)", ""));
                String email = r.findElement(By.xpath("td[3]")).getText();

                user.withUserName(userName).withEmail(email).withId(id);
                break;
            }
        }
        return user;
    }

    public UserData any() {
        UserData user = new UserData();

        WebElement table = wd.findElement(By.xpath("html/body/table[3]"));
        List<WebElement> rows = cleanUsersTable(table);

        for (WebElement r : rows) {
            String userName = r.findElement(By.xpath("td[1]")).getText();
            if (!userName.equals(app.getProperty("web.login"))) {

                int id = Integer.parseInt(r.findElement(By.tagName("a")).getAttribute("href")
                        .replaceAll("(.([^;]*)=)", ""));
                String email = r.findElement(By.xpath("td[3]")).getText();

                user.withUserName(userName).withEmail(email).withId(id);
                break;
            }
        }
        return user;
    }

    public Users all() {
        if (usersCache != null) {
            return new Users(usersCache);
        }
        usersCache = new Users();

        WebElement table = wd.findElement(By.xpath("html/body/table[3]"));
        List<WebElement> rows = cleanUsersTable(table);

        for (WebElement r : rows) {
            String userName = r.findElement(By.xpath("td[1]")).getText();
            if (!userName.equals(app.getProperty("web.login"))) {

                int id = Integer.parseInt(r.findElement(By.tagName("a")).getAttribute("href")
                        .replaceAll("(.([^;]*)=)", ""));
                String email = r.findElement(By.xpath("td[3]")).getText();

                UserData user = new UserData()
                        .withId(id)
                        .withUserName(userName)
                        .withEmail(email);

                usersCache.add(user);
            }
        }
        return new Users(usersCache);
    }

    public List<WebElement> cleanUsersTable(WebElement table) {
        List<WebElement> rows = new ArrayList<WebElement>(table.findElements(By.tagName("tr")));
        rows.remove(rows.size() - 1);
        rows.remove(1);
        rows.remove(0);
        return rows;
    }
}