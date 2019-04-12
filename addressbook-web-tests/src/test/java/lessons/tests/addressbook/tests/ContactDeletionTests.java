package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.ContactData;
import lessons.tests.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0) {
            app.contact().createContact();
        }
    }

    @Test

    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteContact(deletedContact);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.withoutc(deletedContact)));
        verifyGroupListInUI();
    }

}
