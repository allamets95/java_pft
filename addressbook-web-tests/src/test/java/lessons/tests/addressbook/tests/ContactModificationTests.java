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
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Santa", "Claus", "North", "234567", "020000", "1111111", "santa@test.com", "test"));
        }
    }
    @Test

    public void testContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size()- 1;
        ContactData contactData = new ContactData(before.get(index).getId(),"Santa", "Claus", "North", "234567", "020000", "1111111", "santa@test.com", null);
        app.getContactHelper().modifyContact(index, contactData);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals( before, after);

    }

}