package com.pjft.addressbook.tests;

import com.pjft.addressbook.model.GroupData;
import com.pjft.addressbook.model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.csv"));
    String line = reader.readLine();
    while (line != null){
      String[] split = line.split(";");
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() +1));

    assertThat(after, equalTo(before.withAdd(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
  }

}
