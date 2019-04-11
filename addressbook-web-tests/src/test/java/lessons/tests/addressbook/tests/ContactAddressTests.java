package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().allc().size() == 0) {
            app.contact().createContact();
        }
    }

    @Test
    public void testContactAddress(){
        app.goTo().gotoHomePage();
        ContactData contactData =app.contact().allc().iterator().next();
        ContactData contactInfoFromAddressForm = app.contact().infoFromAddressForm(contactData);
        assertThat(contactData.getAddress(), equalTo(cleaned(contactInfoFromAddressForm.getAddress())));
    }
    public String cleaned (String address){
        return address.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}