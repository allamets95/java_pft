package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void  ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().groupList().size() == 0){
            app.group().create(new GroupData("test", "test1", "test2"));
        }
    }

    @Test

    public void testGroupModification() {

        List<GroupData> before = app.group().groupList();
        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(),"test", "test3", "test4");
        app.group().modifyGroup(index, group);
        List<GroupData> after = app.group().groupList();
        Assert.assertEquals(after.size(), before.size() );

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
