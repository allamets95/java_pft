package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public  void ensurePreconditions(){
        if (app.contact().contactList().size() == 0) {
            app.contact().createContact(new ContactData().withFirstname("Santa").withLastname("Claus").withCompany("North").withHome("234567").withMobile("020000").withWork("1111111").withEmail("santa@test.com").withGroup("test"));
        }
    }
    @Test

    public void testContactModification() {
        List<ContactData> before = app.contact().contactList();
        int index = before.size()- 1;
        ContactData contactData = new ContactData().withId(before.get(index).getId())
                .withFirstname("Santa").withLastname("Claus").withCompany("North").withHome("234567").withMobile("020000").withWork("1111111").withEmail("santa@test.com").withGroup("test");
        app.contact().modifyContact(contactData);
        List<ContactData> after = app.contact().contactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals( before, after);

    }

}