package com.pjft.addressbook.appmanager;

import com.pjft.addressbook.model.ContactData;
import com.pjft.addressbook.model.Contacts;
import com.pjft.addressbook.tests.ContactInformationComparison;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
      //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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
    click(By.name("modifiy"));
  }

  public void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector("input[value='" + id + "']"));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.xpath("td"));
    cells.get(7).findElement(By.tagName("a")).click();
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

  public void detailedInfo(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector("input[value='" + id + "']"));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.xpath("td"));
    cells.get(6).findElement(By.tagName("a")).click();
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
    initContactModificationById(contact.getId());
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
      String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
      String email = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
      String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address).withEmail(email).withAllPhones(allPhones).withCreation(false));
    }
    return contacts;
  }

  public String phoneInfoFromContact() {
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    return Arrays.asList(homePhone, mobilePhone, workPhone).stream().filter((s) -> ! s.equals("")).map(ContactHelper::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("-()", "");
  }

  public String addressInfoFromContact() {
    return wd.findElement(By.name("address")).getText();
  }

  public String emailInfoFromContact() {
    return wd.findElement(By.name("email")).getAttribute("value");
  }

  public Contacts collectDetailedInfo() {
    Contacts contact = new Contacts();
    String[] allInfo = wd.findElement(By.cssSelector("#content")).getText().split("\n");
    String[] fullName = allInfo[0].split(" ");
    String firstName = fullName[0];
    String lastName = fullName[1];

    contact.add(new ContactData().withFirstName(firstName).withLastName(lastName).withAddress(allInfo[1]).withEmail(allInfo[7])
            .withHomePhone(allInfo[3].replaceAll(":MHW", "").replaceAll("\\s", ""))
            .withMobilePhone(allInfo[4].replaceAll(":MHW", "").replaceAll("\\s", ""))
            .withWorkPhone(allInfo[5].replaceAll(":MHW", "").replaceAll("\\s", "")));
    return contact;
  }

  public Contacts collectInfo() {
    Contacts contact = new Contacts();
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    contact.add(new ContactData().withFirstName(firstName).withLastName(lastName).withAddress(address)
            .withEmail(email).withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone));
    return contact;
  }

  public void addContact(ContactData contact) {

  }
}
