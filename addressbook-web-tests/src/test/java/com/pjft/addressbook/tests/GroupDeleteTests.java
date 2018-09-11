package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.GroupData;
import com.pjft.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeleteTests extends TestBase{

  @BeforeMethod
  public void ensureGroupPresence(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0){
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testDeleteGroup(){
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    assertThat(after.size() + 1, equalTo(before.size()));

    assertThat(after, equalTo(before.withOut(deletedGroup)));
  }

}
