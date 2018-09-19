package com.pjft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.pjft.addressbook.model.ContactData;
import com.thoughtworks.xstream.XStream;

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

  @Parameter (names = "-d", description = "Data format")
  public String format;

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
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")){
      saveAsCSV(contacts, new File(file));
    } else if (format.equals("xml")){
      saveAsXML(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format "+ format);
    }
  }

  private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
    XStream xstream  = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private static void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;\n", contact.getFirstName(), contact.getLastName(), contact.getAddress(), contact.getEmail(),
              contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i =0; i<count; i++){
      ContactData contact = new ContactData().withFirstName(String.format("name %s", i)).withLastName(String.format("lastname %s", i))
              .withAddress(String.format("address %s", i)).withEmail(String.format("email%s@test.com", i))
              .withHomePhone(String.format("%s23456", i)).withMobilePhone(String.format("%s2 34 56", i)).withWorkPhone(String.format("+7-4%s23456", i)).withCreation(true);
      contacts.add(contact);
    }
    return contacts;
  }
}
