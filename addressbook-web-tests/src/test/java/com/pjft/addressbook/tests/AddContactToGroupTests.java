package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import com.pjft.addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

  @Test
  public void testAddContactToGroup(){
    app.goTo().mainPage();
    Contacts all = app.db().contacts();
    ContactData addedContact = all.iterator().next();
    int beforeAdd = addedContact.getGroups().size();
    int idGroup = verifyContactInGroup(addedContact);
    app.contact().addContact(addedContact, idGroup);

    int afterAdd = app.db().contacts().;
            //addedContact.getGroups().size();

    assertThat(beforeAdd, equalTo(afterAdd-1));
  }

}
