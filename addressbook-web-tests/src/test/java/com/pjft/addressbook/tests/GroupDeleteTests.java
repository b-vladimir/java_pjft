package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeleteTests extends TestBase{

  @BeforeMethod
  public void ensureGroupPresence(){
    if (app.group().list().size() == 0){
      app.group().create(new GroupData("test1", "test2", "test3"));
    }
  }

  @Test
  public void testDeleteGroup(){
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    int index = 0;
    app.group().delete(index);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size() + 1, before.size());

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
