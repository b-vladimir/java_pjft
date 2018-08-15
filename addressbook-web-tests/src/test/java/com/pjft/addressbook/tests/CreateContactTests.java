package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class CreateContactTests extends TestBase {

  @Test
  public void testCreateContact() {
    app.initContactCreation();
    app.fillContactData(new ContactData("Name", "LastName", "Test", "1111", "111",
            "adgjklnbb", "0556953214", "Test@test.com", "test.com", "1990"));
    app.gotoMainPage();
  }

}
