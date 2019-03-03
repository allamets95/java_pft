package lessons.tests.addressbook.tests;


import lessons.tests.addressbook.model.ContactData;
import org.testng.annotations.*;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().gotoAddContact();
    app.getContactHelper().fillContactForm(new ContactData("Santa", "Claus", "North", "234567", "020000", "1111111", "santa@test.com"));
    app.getContactHelper().enterContactCreation();
    app.getNavigationHelper().returnToHomePage();
    app.logout();
  }


}
