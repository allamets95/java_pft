package lessons.tests.addressbook.tests;


import lessons.tests.addressbook.model.ContactData;
import lessons.tests.addressbook.model.Contacts;
import org.testng.annotations.*;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class ContactCreationTests extends TestBase {




  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().allc();
    File photo = new File("src/test/resources/sqa.png");
    ContactData contactData = new ContactData().withFirstname("Santa").withLastname("Claus");
    app.contact().createContact(contactData);
    assertThat(app.contact().сontactCount(), equalTo( before.size()+ 1));
    Contacts after= app.contact().allc();
  }


}

