package com.pjft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pjft.addressbook.model.GroupData;
import com.pjft.addressbook.model.Groups;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validGroupsXML() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"));
    String xml ="";
    String line = reader.readLine();
    while (line != null){
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<GroupData> group = (List<GroupData>) xstream.fromXML(xml);
    return group.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"));){
      String json ="";
      String line = reader.readLine();
      while (line != null){
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> group = gson.fromJson(json, new TypeToken<List<GroupData>>() {}.getType());
      return group.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsJSON")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.db().groups();
    app.group().create(group);
    Groups after = app.db().groups();
    assertThat(after.size(), equalTo(before.size() +1));

    assertThat(after, equalTo(before.withAdd(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    verifyGroupListInUI();
  }

}
