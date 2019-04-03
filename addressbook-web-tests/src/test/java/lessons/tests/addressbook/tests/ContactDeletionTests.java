package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().contactList().size() == 0) {
            app.contact().createContact(new ContactData().withFirstname("Santa").withLastname("Claus").withCompany("North").withHome("234567").withMobile("020000").withWork("1111111").withEmail("santa@test.com").withGroup("test"));
        }
    }

    @Test

    public void testContactDeletion() {
        List<ContactData> before = app.contact().contactList();
        int index = before.size() -1;
        app.contact().deleteContact(index);
        List<ContactData> after = app.contact().contactList();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
