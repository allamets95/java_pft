package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.ContactData;
import lessons.tests.addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().allc().size() == 0) {
            app.contact().createContact(new ContactData().withFirstname("Santa").withLastname("Claus").withCompany("North").withHome("234567").withMobile("020000").withWork("1111111").withEmail("santa@test.com").withGroup("test"));
        }
    }

    @Test

    public void testContactDeletion() {
        Contacts before = app.contact().allc();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteContact(deletedContact);
        Contacts after = app.contact().allc();
        assertEquals(after.size(), before.size() -1);
        assertThat(after, equalTo(before.withoutc(deletedContact)));
    }

}
