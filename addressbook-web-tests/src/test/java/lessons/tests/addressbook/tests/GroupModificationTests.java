package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

    @Test

    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        int before  = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test", "test1", "test2"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test", "test3", "test4"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        int after  = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before );
    }
}
