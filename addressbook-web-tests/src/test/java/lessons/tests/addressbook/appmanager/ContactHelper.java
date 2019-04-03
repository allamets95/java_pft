package lessons.tests.addressbook.appmanager;

import lessons.tests.addressbook.model.ContactData;
import lessons.tests.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void enterContactCreation() {
        click(By.xpath("//*[@id=\"content\"]/form/input[1]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("email"), contactData.getEmail());

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

    public void createContact(ContactData contactData) {
        gotoAddContact();
        fillContactForm(new ContactData().withFirstname("Santa").withLastname("Claus").withCompany("North").withHome("234567").withMobile("020000").withWork("1111111").withEmail("santa@test.com").withGroup("test"));
        enterContactCreation();
        returnToHomePage();
    }

    public void modifyContact(ContactData contactData) {
        editContactModification(contactData.getId());
        fillContactForm(contactData);
        updateContactModification();
        returnToHomePage();
    }

    public void deleteContact(ContactData contactData) {
        selectContactById(contactData.getId());
        deleteSelectedContact();
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

    public int getContactCount() {
       return wd.findElements(By.name("selected[]")).size();

    }

    public Contacts allc() {
       Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry")); //By.cssSelector("tr")By.name("entry")
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname =  element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

}
