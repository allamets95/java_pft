package lessons.tests.addressbook.appmanager;

import lessons.tests.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactHelper extends HelperBase  {

    public ContactHelper(ChromeDriver wd) {

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


    public void editContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void updateContactModification() {
        click(By.name("update"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmDeletion() {
        acceptAlert();
    }
}
