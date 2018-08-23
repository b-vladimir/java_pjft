package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeleteTests extends TestBase{

  @Test
  public void testDeleteGroup(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupDelete();
    app.getNavigationHelper().gotoGroupPage();
  }
}
