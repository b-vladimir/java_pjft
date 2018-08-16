package com.pjft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase{

  @Test
  public void testDeleteContact(){
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactDelete();
    app.getNavigationHelper().acceptDialogWindow();
    app.getNavigationHelper().gotoMainPage();
  }
}
