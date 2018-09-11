package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeleteTests extends TestBase{

  @BeforeMethod
  public void ensureGroupPresence(){
    app.goTo().groupPage();
    if (app.group().list().size() == 0){
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testDeleteGroup(){
    List<GroupData> before = app.group().list();
    int index = 0;
    app.group().delete(index);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size() + 1, before.size());

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
