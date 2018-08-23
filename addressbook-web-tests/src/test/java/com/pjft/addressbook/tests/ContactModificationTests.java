package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Name", "LastName", "Test", "1111", "111",
              "adgjklnbb", "0556953214", "Test@test.com", "test.com", "1990", "test1", true));
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactData(new ContactData("Name edit", "LastName edit", "Test", "1111", "111",
            "adgjklnbb", "0556953214", "Test@test.com", "test.com", "1990", null, false));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoMainPage();
  }
}
