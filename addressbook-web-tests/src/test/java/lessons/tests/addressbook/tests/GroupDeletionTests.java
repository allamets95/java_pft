package lessons.tests.addressbook.tests;


import lessons.tests.addressbook.model.GroupData;
import lessons.tests.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void  ensurePreconditions(){

    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().createGroup(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
    }
  }
  @Test
  public void testGroupDeletion() throws Exception {
    app.goTo().groupPage();
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
        app.group().deleteGroup(deletedGroup);
    assertThat(app.group().сount(), equalTo(before.size() - 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListInUI();
  }



}
