package lessons.tests.addressbook.tests;


import lessons.tests.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        int before  = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("test", "test1", "test2"));
        int after  = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
        app.logout();
    }

}
