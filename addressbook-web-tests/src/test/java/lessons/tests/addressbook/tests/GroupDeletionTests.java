package lessons.tests.addressbook.tests;


import lessons.tests.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;


public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void  ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().groupList().size() == 0){
      app.group().create(new GroupData("test", "test1", "test2"));
    }
  }
  @Test
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.group().groupList();
    int index = before.size()- 1 ;
    app.group().delete(index);
    List<GroupData> after = app.group().groupList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
      Assert.assertEquals(before, after);
  }



}
