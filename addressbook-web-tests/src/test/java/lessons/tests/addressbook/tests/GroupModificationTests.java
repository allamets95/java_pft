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
    public void  ensurePreconditions() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
        }
    }

    @Test

    public void testGroupModification() {

        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test2").withHeader("test3").withFooter("test4");
        app.goTo().groupPage();
        app.group().modifyGroup(group);
        assertThat(app.group().сount(), equalTo(before.size()));
        Groups after= app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();
    }

    public void verifyGroupListInUI() {
    }

}
