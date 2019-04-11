package lessons.tests.addressbook.tests;
import lessons.tests.addressbook.model.ContactData;
import lessons.tests.addressbook.model.GroupData;
import lessons.tests.addressbook.model.Groups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddContactToGroupTests extends TestBase {

    @BeforeClass
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().returnToHomePage();
            app.contact().createContact();
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData()
                    .withName("Test"));
        }
        for (ContactData contact : app.db().contacts()) {
            if (contact.getGroups().size() < app.db().groups().size()) {
                break;
            } else {
                app.goTo().groupPage();
                app.group().createGroup(new GroupData()
                        .withName("Tessssst"));
            }
        }
    }

    @Test
    public void testAddContactToGroup() {
        ContactData contact = null;
        for (ContactData newContact : app.db().contacts())
            if (newContact.getGroups().size() < app.db().groups().size()) {
                contact = newContact;
            }
        Groups before = contact.getGroups();
        Groups allGroups = app.db().groups();
        allGroups.removeAll(before);
        GroupData group = allGroups.iterator().next();
        app.goTo().gotoHomePage();
        app.group().selectNew(group.getId());
        app.contact().selectContactById(contact.getId());
        app.contact().addContactToGroup();
        Groups after = app.db().contactByIdInDB(contact.getId()).iterator().next().getGroups();
        assertEquals(before.withAdded(group), after);
    }
}