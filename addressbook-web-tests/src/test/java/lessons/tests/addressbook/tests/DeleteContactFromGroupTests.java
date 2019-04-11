package lessons.tests.addressbook.tests;

import lessons.tests.addressbook.model.ContactData;
import lessons.tests.addressbook.model.GroupData;
import lessons.tests.addressbook.model.Groups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DeleteContactFromGroupTests extends TestBase {

    @BeforeClass
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().returnToHomePage();
            app.contact().createContact();
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData()
                    .withName("Test 16"));
        }
    }

    @Test
    public void testDeleteContactFromGroup() {
        Groups before = null;
        GroupData availableGroup = null;
        ContactData availableContact = null;
        int groupId = 0;
        for (ContactData contact : app.db().contacts()) {
            if (contact.getGroups().size() == 0) {
                app.goTo().gotoHomePage();
                app.contact().selectContactById(contact.getId());
                app.contact().addContactToGroup();
                availableContact = contact;
                availableGroup = app.db().contactByIdInDB(availableContact.getId()).iterator().next().getGroups().iterator().next();
                groupId = availableGroup.getId();
                before = contact.getGroups();
            } else {
                availableContact = contact;
                availableGroup = contact.getGroups().iterator().next();
                groupId = availableGroup.getId();
                before = contact.getGroups();
            }
        }
        app.goTo().gotoHomePage();
        app.group().selectGroupByIdOnGroupPage(groupId);
        app.contact().selectContactById(availableContact.getId());
        app.contact().removeFromGroup();
        //app.goTo().groupPage();
        Groups after = app.db().contactByIdInDB(availableContact.getId()).iterator().next().getGroups();
        assertEquals(before.without(availableGroup), after);
    }
}
