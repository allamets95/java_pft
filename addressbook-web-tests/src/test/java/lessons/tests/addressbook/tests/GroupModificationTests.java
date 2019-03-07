package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

    @Test

    public void testGroupModification() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test", "test1", "test2"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

    }
}
