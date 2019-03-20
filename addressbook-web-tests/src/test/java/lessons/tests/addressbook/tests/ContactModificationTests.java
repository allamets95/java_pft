package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test

    public void  testContactModification() {

        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Santa", "Claus", "North", "234567", "020000", "1111111", "santa@test.com", "test"));
        }
        app.getContactHelper().editContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Fanta", "Claus", "North", "234567", "020000", "1111111", "santa@test.com", null));
        app.getContactHelper().updateContactModification();
        app.getNavigationHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before );
    }

}
