package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.GroupData;
import com.pjft.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensureGroupPresence(){
    app.goTo().groupPage();
    if (app.db().groups().size() == 0){
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testGroupModification(){
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1 edit").withHeader("test2").withFooter("test3");
    app.group().modify(group);
    Groups after = app.db().groups();
    assertThat(after.size(), equalTo(before.size()));

    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdd(group)));
    verifyGroupListInUI();
  }

}
