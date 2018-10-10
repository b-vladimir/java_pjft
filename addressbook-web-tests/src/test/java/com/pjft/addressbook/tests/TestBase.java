package com.pjft.addressbook.tests;

import com.pjft.addressbook.appmanager.ApplicationManager;
import com.pjft.addressbook.model.ContactData;
import com.pjft.addressbook.model.GroupData;
import com.pjft.addressbook.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups fromDB = app.db().groups();
      Groups fromUI = app.group().all();
      assertThat(fromUI, equalTo(fromDB.stream().map((g) -> new GroupData()
              .withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
    }
  }

  public int verifyContactInGroup(ContactData contact) {
    int idGroup = 0;
    if (contact.getGroups().size() == app.db().groups().size()){
      app.group().create(new GroupData().withName("testName").withHeader("testHeader").withFooter("testFooter"));
      idGroup = app.db().groups().stream().mapToInt(GroupData::getId).max().getAsInt();
    }else {
      Groups groups = app.db().groups();
      for (GroupData group : contact.getGroups()) {
        groups = groups.withOut(group);
      }
      idGroup = groups.iterator().next().getId();
    }
    return idGroup;
  }
}
