package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.ContactData;
import com.pjft.addressbook.model.GroupData;
import com.pjft.addressbook.model.Groups;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTest extends TestBase{

  @Test
  public void testDeleteContactFromGroup(){
    app.goTo().mainPage();
    chooseGroupSet();
    ContactData deletedContact = app.contact().all().iterator().next();
    int beforeDelete = app.db().contacts().stream().filter(data -> Objects.equals(data.getId(), deletedContact.getId()))
            .findFirst().get().getGroups().size();
    app.contact().deleteContactFromGroup(deletedContact);
    int afterDelete = app.db().contacts().stream().filter(data -> Objects.equals(data.getId(), deletedContact.getId()))
            .findFirst().get().getGroups().size();

    assertThat(beforeDelete, equalTo(afterDelete+1));
  }

}
