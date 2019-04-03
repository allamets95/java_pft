package lessons.tests.addressbook.tests;


import lessons.tests.addressbook.model.GroupData;
import lessons.tests.addressbook.model.Groups;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().allg();
        GroupData group = new GroupData().withName("test").withHeader("test1").withFooter("test2");
        app.group().createGroup(group);
        Groups after = app.group().allg();
        assertThat(after.size(), equalTo(before.size() + 1));


        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        app.logout();
    }


}
