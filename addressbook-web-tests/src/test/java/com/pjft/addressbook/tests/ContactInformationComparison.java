package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import com.pjft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationComparison extends TestBase {

  @BeforeMethod
  public void ensureContactPresence(){
    app.goTo().mainPage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstName("Name").withLastName("LastName").withAddress("testAdress").withEmail("Test@test.com")
              .withHomePhone("0556953214").withMobilePhone("123").withWorkPhone("+986554").withGroup("test1 edit").withCreation(true));
    }
  }

  @Test
  public void testContactComparison(){
    ContactData contact = app.contact().all().iterator().next();
    app.contact().initContactModificationById(contact.getId());
    String phonesFromForm = app.contact().phoneInfoFromContact();
    String addressFromForm = app.contact().addressInfoFromContact();
    String emailFromForm = app.contact().emailInfoFromContact();

    assertThat(contact.getAllPhones(), equalTo(phonesFromForm));
    assertThat(contact.getAddress(), equalTo(addressFromForm));
    assertThat(contact.getEmail(), equalTo(emailFromForm));
  }

  @Test
  public void testDetailedInformation(){
    ContactData contact = app.contact().all().iterator().next();
    app.contact().detailedInfo(contact.getId());
    Contacts detailedInfo = app.contact().collectDetailedInfo();
    app.contact().initContactModification();
    Contacts fromEditPage = app.contact().collectInfo();

    assertThat(detailedInfo, equalTo(fromEditPage));
  }

}
