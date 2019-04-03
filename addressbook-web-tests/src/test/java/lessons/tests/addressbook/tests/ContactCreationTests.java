package lessons.tests.addressbook.tests;


import lessons.tests.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.contact().contactList();
    ContactData contactData = new ContactData().withFirstname("Santa").withLastname("Claus").withCompany("North").withHome("234567").withMobile("020000").withWork("1111111").withEmail("santa@test.com").withGroup("test");
    app.contact().createContact(contactData);
    List<ContactData> after= app.contact().contactList();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(contactData);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
