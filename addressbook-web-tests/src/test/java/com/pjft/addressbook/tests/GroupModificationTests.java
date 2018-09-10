package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensureGroupPresence(){
    if (app.group().list().size() == 0){
      app.group().create(new GroupData("test1", "test2", "test3"));
    }
  }

  @Test
  public void testGroupModification(){
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    int index = 0;
    GroupData group = new GroupData(before.get(index).getId(), "test1 edit", "test2", "test3");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> ById = Comparator.comparingInt(GroupData::getId);
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }

}
