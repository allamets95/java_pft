package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Santa", "Claus", "North", "234567", "020000", "1111111", "santa@test.com", "test"));
        }
    }

    @Test

    public void testContactDeletion() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() -1;
        app.getContactHelper().deleteContact(index);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
