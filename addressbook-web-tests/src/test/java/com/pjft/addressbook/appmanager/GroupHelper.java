package com.pjft.addressbook.appmanager;

import com.pjft.addressbook.model.GroupData;
import com.pjft.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void selectGroup(int i) {
    wd.findElements(By.name("selected[]")).get(i).click();
  }

  public void selectGroupById(int i) {
    wd.findElement(By.cssSelector("input[value='" + i + "']")).click();
  }


  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void initGroupDelete() {
    click(By.name("delete"));
  }

  public void returnToGroupPage(){
    click(By.linkText("groups"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returnToGroupPage();
  }

  public void delete(int index) {
    selectGroup(index);
    initGroupDelete();
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    initGroupDelete();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return  isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return  wd.findElements(By.name("selected[]")).size();
  }

  public Groups all() {
    Groups groups = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }

}
