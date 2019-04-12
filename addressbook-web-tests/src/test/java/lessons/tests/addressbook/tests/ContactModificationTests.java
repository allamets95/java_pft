package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.ContactData;
import lessons.tests.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public  void ensurePreconditions(){
        if (app.db().contacts().size() == 0) {
            app.contact().createContact();
        }
    }
    @Test

    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contactData = new ContactData().withId(modifiedContact.getId())
                .withFirstname("James").withLastname("Claus").withCompany("North").withAddress("Cold").withHome("234567").withMobile("020000").withWork("1111111").withEmail("santa@test.com").withEmail2("test@test.com").withEmail3("test@test.com");
        app.contact().modifyContact(contactData);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.withoutc(modifiedContact).withAddedc(contactData)));
        verifyGroupListInUI();
    }

}