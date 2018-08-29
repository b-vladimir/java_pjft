package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase{

  @Test
  public void testDeleteContact(){
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Name", "LastName", "Test", "1111", "111",
              "adgjklnbb", "0556953214", "Test@test.com", "test.com", "1990", "test1", true));
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactDelete();
    app.getNavigationHelper().acceptDialogWindow();
    app.getNavigationHelper().gotoMainPage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after + 1, before);
  }
}
