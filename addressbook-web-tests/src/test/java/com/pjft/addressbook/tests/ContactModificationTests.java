package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensureContactPresence(){
    app.goTo().mainPage();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData("Name", "LastName", "Test", "1111", "111",
              "adgjklnbb", "0556953214", "Test@test.com", "test.com", "1990", "test1", true));
    }
  }

  @Test
  public void testContactModification(){
    ContactData contact = new ContactData("Name edit", "LastName edit", "Test", "1111", "111",
            "adgjklnbb", "0556953214", "Test@test.com", "test.com", "1990", null, false);
    app.contact().modify(contact);
  }
}
