package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import com.pjft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTests extends TestBase{

  @BeforeMethod
  public void ensureContactPresence(){
    app.goTo().mainPage();
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstName("Name").withLastName("LastName").withAddress("testAdress")
              .withEmail("Test@test.com").withHomePhone("0556953214").withMobilePhone("123").withWorkPhone("+986554")
              .withCreation(true).inGroup(app.db().groups().iterator().next()));
    }
  }

  @Test
  public void testDeleteContact(){
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();
    assertThat(after.size() + 1, equalTo(before.size()));

    before.remove(deletedContact);
    assertThat(after, equalTo(before.withOut(deletedContact)));
  }

}
