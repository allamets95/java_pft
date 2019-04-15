package lessons.tests.addressbook.appmanager;

import lessons.tests.addressbook.model.ContactData;
import lessons.tests.addressbook.model.Contacts;
import lessons.tests.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.util.List;



public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void enterContactCreation() {
        click(By.xpath("//*[@id=\"content\"]/form/input[1]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        if (contactData.getGroups().size() > 0) {
            Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());

        }
        else {
            type(By.name("firstname"), contactData.getFirstname());
            type(By.name("lastname"), contactData.getLastname());
            Assert.assertFalse(isElementPresent(By.name("new_group")));

        }
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
    }

    public void editContactModification(int id) {

        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void updateContactModification() {
        click(By.name("update"));
    }


    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }
    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmDeletion() {

        acceptAlert();
    }

    public void createContact() {
        gotoAddContact();
        File photo =new File("src/test/resources/sqa.png");
<<<<<<< HEAD
        ContactData contactData= new ContactData().withFirstname("Santa").withLastname("Claus").withCompany("North").withPhoto(photo).withAddress("Cold")
                .withHome("234567").withMobile("020000").withWork("1111111").withEmail("santa@test.com").withEmail2("test@test.com").withEmail3("test@test.com");
        fillContactForm(contactData,true);
=======
        fillContactForm(contactData);
>>>>>>> Rework15
        contactCache = null;
        enterContactCreation();
        returnToHomePage();
    }

    public void modifyContact(ContactData contactData) {
        editContactModification(contactData.getId());
        fillContactForm(contactData, false);
        contactCache = null;
        updateContactModification();
        returnToHomePage();
    }

    public void deleteContact(ContactData contactData) {
        selectContactById(contactData.getId());
        deleteSelectedContact();
        contactCache = null;
        confirmDeletion();
        returnToHomePage();
    }

    public void returnToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    private void gotoAddContact() {
        click(By.linkText("add new"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int сontactCount() {
        return wd.findElements(By.name("selected[]")).size();

    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
    private Contacts contactCache = null;


    public Contacts allc() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry")); //By.cssSelector("tr")By.name("entry")
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname =  element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones).withAddress(address)
                    .withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }
    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHome(home).withMobile(mobile).withWork(work);
    }
    public ContactData infoFromAddressForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().
                withId(contact.getId()).withAddress(address);
    }

    public ContactData infoFromEmailForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().
                withId(contact.getId()).withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public void addContactToGroup() {
        wd.findElement(By.name("add")).click();
    }

    public void removeFromGroup() {
        wd.findElement(By.cssSelector("input[name='remove']")).click();
    }
}