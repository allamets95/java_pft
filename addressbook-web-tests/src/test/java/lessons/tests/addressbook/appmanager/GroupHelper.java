package lessons.tests.addressbook.appmanager;

import lessons.tests.addressbook.model.GroupData;
import lessons.tests.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends  HelperBase{

    public GroupHelper(WebDriver wd) {

        super(wd);

    }

    public  void returnToGroupPage() {
        click(By.linkText("group page"));
    }


    public  void submitGroupCreation() {
        click(By.name("submit"));
    }

    public  void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }


    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {

        click(By.name("edit"));
    }

    public void submitGroupModification() {

        click(By.name("update"));
    }

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }
    public void modifyGroup(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public void deleteGroup(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
      return   wd.findElements(By.name("selected[]")).size();
    }

    public Groups allg() {
        Groups groups = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element: elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }


}
