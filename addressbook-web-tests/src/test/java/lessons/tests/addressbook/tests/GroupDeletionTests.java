package lessons.tests.addressbook.tests;


import lessons.tests.addressbook.model.GroupData;
import org.testng.annotations.*;


public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test", "test1", "test2"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }


}
