package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testCreateContact() {
    app.goTo().mainPage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstName("Name").withLastName("LastName").withAddress("testAdrress").withEmail("Test@test.com")
            .withHomePhone("0556953214").withMobilePhone("123").withWorkPhone("+986554").withGroup("test1 edit").withCreation(true);
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() +1);

    Comparator<? super ContactData> ById = Comparator.comparingInt(ContactData::getId);
    contact.withId(after.stream().max(ById).get().getId());
    before.add(contact);
    after.sort(ById);
    before.sort(ById);
    Assert.assertEquals(after, before);
  }

}
