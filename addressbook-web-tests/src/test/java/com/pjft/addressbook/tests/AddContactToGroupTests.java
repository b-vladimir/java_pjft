package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import com.pjft.addressbook.model.Contacts;
import org.testng.annotations.Test;

public class AddContactToGroupTests extends TestBase {

  @Test
  public void testAddContactToGroup(){
    app.goTo().mainPage();
    Contacts all = app.db().contacts();
    ContactData addedContact = all.iterator().next();
    app.contact().addContact(addedContact);
  }
}
