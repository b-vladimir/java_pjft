package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeleteTests extends TestBase{

  @BeforeMethod
  public void ensureContactPresence(){
    app.goTo().mainPage();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData("Name", "LastName", "Test", "1111", "111",
              "adgjklnbb", "0556953214", "Test@test.com", "test.com", "1990", "test1", true));
    }
  }

  @Test
  public void testDeleteContact(){
    List<ContactData> before = app.contact().list();
    int index = 0;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size() + 1, before.size());

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
