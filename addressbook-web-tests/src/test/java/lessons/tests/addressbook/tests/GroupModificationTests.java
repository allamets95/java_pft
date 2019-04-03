package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.GroupData;
import lessons.tests.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void  ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().allg().size() == 0){
            app.group().createGroup(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
        }
    }

    @Test

    public void testGroupModification() {

        Groups before = app.group().allg();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test2").withHeader("test3").withFooter("test4");
        app.group().modifyGroup(group);
        Groups after = app.group().allg();
        assertEquals(after.size(), before.size() );
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
