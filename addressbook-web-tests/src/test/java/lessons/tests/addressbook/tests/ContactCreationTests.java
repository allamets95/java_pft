package lessons.tests.addressbook.tests;


import lessons.tests.addressbook.model.ContactData;
import org.testng.annotations.*;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().createContact(new ContactData("Santa", "Claus", "North", "234567", "020000", "1111111", "santa@test.com", "test"));
  }


}
