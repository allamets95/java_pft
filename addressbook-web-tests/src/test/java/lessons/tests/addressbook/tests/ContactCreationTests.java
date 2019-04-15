package lessons.tests.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import lessons.tests.addressbook.model.ContactData;
import lessons.tests.addressbook.model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class ContactCreationTests extends TestBase {


  @DataProvider
  public Iterator<Object[]> validContactsCsv() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
      String line = reader.readLine();
      while (line != null) {
        String split[] = line.split(";");
        list.add(new Object[]{new ContactData()
                .withFirstname(split[0])
                .withLastname(split[1])
                .withCompany(split[2]).withMobile(split[3]).withHome(split[4])
                .withWork(split[5]).withEmail(split[6])
                .withEmail2(split[7]).withEmail3(split[8])
                .withAddress(split[9]).withPhoto(split[10])});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }
  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((с) -> new Object[]{с}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((с) -> new Object[]{с}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation()  {
    app.goTo().gotoHomePage();
    Contacts before = app.db().contacts();
<<<<<<< HEAD
    app.contact().createContact();
=======
    app.contact().createContact(contactData);
>>>>>>> Rework15
    Contacts after = app.db().contacts();
    assertThat(app.contact().сontactCount(), equalTo( before.size()+ 1));
    assertThat(after, equalTo(before.withAddedc(contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}