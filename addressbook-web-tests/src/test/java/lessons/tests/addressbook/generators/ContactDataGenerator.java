package lessons.tests.addressbook.generators;

import lessons.tests.addressbook.model.ContactData;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names ="-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Targets file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.runs();
    }

    private void runs() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        }else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        }else
        {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }


    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;\n", contact.getFirstname(), contact.getLastname()));
        }
        writer.close();
    }
    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("Santa%s", i)).withLastname(String.format("Clause%s", i))
                    .withGroup("test").withCompany(String.format("North%s", i)).withMobile(String.format("+38099999999%s", i))
                    .withHome(String.format("+38000000002%s", i)).withWork(String.format("+38000000000%s", i))
                    .withEmail(String.format("testemail%s@mail.ru", i)).withEmail2(String.format("testemail2%s@mail.ru", i))
                    .withEmail3(String.format("testemail3%s@mail.ru", i)).withAddress(String.format("Ukraine %s", i,i)));

        }
        return contacts;
    }
}
