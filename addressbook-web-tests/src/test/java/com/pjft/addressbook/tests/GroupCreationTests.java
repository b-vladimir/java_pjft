package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupCreation();
    app.getNavigationHelper().gotoGroupPage();
  }

}