package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import com.pjft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensureContactPresence(){
    app.goTo().mainPage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstName("Name").withLastName("LastName").withAddress("testAdress").withEmail("Test@test.com")
              .withHomePhone("0556953214").withMobilePhone("123").withWorkPhone("+986554").withGroup("test1 edit").withCreation(true));
    }
  }

  @Test
  public void testContactModification(){
    Contacts before = app.contact().all();
    ContactData modifiedConcact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedConcact.getId()).withFirstName("Name edit").withLastName("LastName edit").withAddress("testAddress edit").withEmail("Test@test.com")
            .withHomePhone("0556953214").withMobilePhone("123").withWorkPhone("+986554").withCreation(false);
    app.contact().modify(contact);
  }
}
