package com.pjft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.pjft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  public static void main(String [] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateGroups(count);
    save(contacts, new File(file));
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
