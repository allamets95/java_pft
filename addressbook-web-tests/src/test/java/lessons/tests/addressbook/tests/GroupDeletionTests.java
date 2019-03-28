package lessons.tests.addressbook.tests;


import lessons.tests.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;


public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void  ensurePreconditions(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test", "test1", "test2"));
    }
  }
  @Test
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size()- 1 ;
    app.getGroupHelper().deleteGroup(index);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
      Assert.assertEquals(before, after);
  }



}
