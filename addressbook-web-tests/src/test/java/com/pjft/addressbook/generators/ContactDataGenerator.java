package com.pjft.addressbook.generators;

import com.pjft.addressbook.model.ContactData;
import com.pjft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  public static void main(String [] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateGroups(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;\n", contact.getFirstName(), contact.getLastName(), contact.getAddress(), contact.getEmail(),
              contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()));
    }
    writer.close();
  }

  private static List<ContactData> generateGroups(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i =0; i<count; i++){
      ContactData contact = new ContactData().withFirstName(String.format("name %s", i)).withLastName(String.format("lastname %s", i))
              .withAddress(String.format("address %s", i)).withEmail(String.format("email%s@test.com", i))
              .withHomePhone(String.format("%s23456", i)).withMobilePhone(String.format("%s2 34 56", i)).withWorkPhone(String.format("+7-4%s23456", i));
      contacts.add(contact);
    }
    return contacts;
  }
}
