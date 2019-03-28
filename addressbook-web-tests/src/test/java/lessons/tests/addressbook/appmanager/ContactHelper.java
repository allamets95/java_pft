package lessons.tests.addressbook.appmanager;

import lessons.tests.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;


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

        click(By.xpath("//tr[@class='odd']/td[8]/a"));
        //click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));
    }

    public void updateContactModification() {
        click(By.name("update"));
    }

    public void selectContact(int index) {
       wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmDeletion() {

        acceptAlert();
    }

    public void createContact(ContactData contactData) {
        gotoAddContact();
        fillContactForm(new ContactData("Santa", "Claus", "North", "234567", "020000", "1111111", "santa@test.com", "test"));
        enterContactCreation();
        returnToHomePage();
    }

    public void modifyContact(int index, ContactData contactData) {
        editContactModification(index);
        fillContactForm(contactData);
        updateContactModification();
        returnToHomePage();
    }

    public void deleteContact(int index) {
        selectContact(index);
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

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry")); //By.cssSelector("tr")By.name("entry")
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname =  element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            ContactData contactData = new ContactData(id, firstname, lastname, null, null, null, null, null, null);
            contacts.add(contactData);
        }
        return contacts;
    }
}
