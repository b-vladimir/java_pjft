package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.GroupData;
import com.pjft.addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() +1));

    assertThat(after, equalTo(before.withAdd(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
  }

}
