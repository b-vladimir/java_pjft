package com.pjft.addressbook;

import org.testng.annotations.Test;

import org.openqa.selenium.*;

public class CreateContactTests extends TestBase {

  @Test
  public void testCreateContact() {
    login("admin", "secret");
    initContactCreation();
    fillContactData(new ContactData("Name", "LastName", "Test", "1111", "111", "adgjklnbb", "0556953214", "Test@test.com", "test.com", "1990"));
    gotoMainPage();
  }

}
