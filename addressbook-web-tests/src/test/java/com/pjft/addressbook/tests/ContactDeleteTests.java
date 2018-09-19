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
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstName("Name").withLastName("LastName").withAddress("testAdress").withEmail("Test@test.com")
              .withHomePhone("0556953214").withMobilePhone("123").withWorkPhone("+986554").withGroup("test1 edit").withCreation(true));
    }
  }

  @Test
  public void testDeleteContact(){
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertThat(after.size() + 1, equalTo(before.size()));

    before.remove(deletedContact);
    assertThat(after, equalTo(before.withOut(deletedContact)));
  }

}
