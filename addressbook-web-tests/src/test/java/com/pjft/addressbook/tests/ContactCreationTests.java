package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testCreateContact() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreation();
    ContactData contact = new ContactData("Name", "LastName", "Test", "1111", "111",
            "adgjklnbb", "0556953214", "Test@test.com", "test.com", "1990", "test1 edit", true);
    app.getContactHelper().fillContactData(contact);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoMainPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);

    Comparator<? super ContactData> ById = Comparator.comparingInt(ContactData::getId);
    contact.setId(after.stream().max(ById).get().getId());
    before.add(contact);
    after.sort(ById);
    before.sort(ById);
    Assert.assertEquals(after, before);
  }

}
