package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    GroupData group = new GroupData("test1", "test2", "test3");
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() +1);

    Comparator<? super GroupData> ById = Comparator.comparingInt(GroupData::getId);
    group.setId(after.stream().max(ById).get().getId());
    before.add(group);
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }

}
