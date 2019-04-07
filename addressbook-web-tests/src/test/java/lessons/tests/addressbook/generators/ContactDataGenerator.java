package lessons.tests.addressbook.generators;

import lessons.tests.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    public static void main (String [] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts (count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact: contacts){
            writer.write(String.format("%s;%s;\n", contact.getFirstname(), contact.getLastname()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData>contacts = new ArrayList<ContactData>();
        for (int i = 0; i< count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("firstName %s", i)).
                    withLastname(String.format("lastName %s", i)));
        }
        return contacts;
    }
}