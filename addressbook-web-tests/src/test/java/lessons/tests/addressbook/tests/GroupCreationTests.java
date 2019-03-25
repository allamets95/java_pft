package lessons.tests.addressbook.tests;


import lessons.tests.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        //int before  = app.getGroupHelper().getGroupCount();
        GroupData group = new GroupData("test", "test1", "test2");
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        //int after  = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(), before.size() + 1);


        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
        app.logout();
    }


}
