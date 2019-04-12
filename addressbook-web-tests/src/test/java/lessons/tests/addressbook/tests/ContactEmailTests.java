package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().allc().size() == 0) {
            app.contact().createContact();
        }
    }

    @Test
    public void testContactEmails(){
        app.goTo().gotoHomePage();
        ContactData contactData =app.contact().allc().iterator().next();
        ContactData contactInfoFromEmailForm = app.contact().infoFromEmailForm(contactData);
        assertThat(contactData.getAllEmails(), equalTo(mergeEmails(contactInfoFromEmailForm)));

    }

    private String mergeEmails(ContactData contactData) {
        return Arrays.asList(contactData.getEmail(), contactData.getEmail2(), contactData.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned (String phone){
        return (String) phone.replaceAll("\\s", "").replaceAll("[-()]", "");

    }
}