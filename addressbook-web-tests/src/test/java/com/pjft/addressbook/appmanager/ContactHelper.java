package com.pjft.addressbook.appmanager;

import com.pjft.addressbook.model.ContactData;
import com.pjft.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactData(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getAvatar());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompanyInfo());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("homepage"), contactData.getSite());
    selectCheckBox(By.xpath("//div[@id='content']/form/select[1]//option[3]"));
    selectCheckBox(By.xpath("//div[@id='content']/form/select[2]//option[2]"));
    type(By.name("byear"), contactData.getYear());
    if (contactData.isCreation()) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void initContactModification() {
    click(By.cssSelector("#maintable > tbody > tr:nth-child(2) > td:nth-child(8) > a > img"));
  }

  public void submitContactModification() {
    click(By.cssSelector("#content > form:nth-child(2) > input[type=\"submit\"]:nth-child(86)"));
  }

  public void selectContact(int i) {
    wd.findElements(By.name("selected[]")).get(i).click();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initContactDelete() {
    click(By.cssSelector("#content > form:nth-child(10) > div:nth-child(8) > input[type=\"button\"]"));
  }

  private void returnToContactPage() {
    click(By.linkText("home"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactData(contact);
    submitContactCreation();
    returnToContactPage();
  }

  public void modify(ContactData contact) {
    initContactModification();
    fillContactData(contact);
    submitContactModification();
    returnToContactPage();
  }

  public void delete(int index) {
    selectContact(index);
    initContactDelete();
    acceptDialogWindow();
    returnToContactPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    initContactDelete();
    acceptDialogWindow();
    returnToContactPage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements){
      int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) > input")).getAttribute("value"));
      String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withCreation(false));
    }
    return contacts;
  }

}
