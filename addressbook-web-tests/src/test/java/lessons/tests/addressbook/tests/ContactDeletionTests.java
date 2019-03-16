package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test

    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Santa", "Claus", "North", "234567", "020000", "1111111", "santa@test.com", "test"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().confirmDeletion();

    }
}
