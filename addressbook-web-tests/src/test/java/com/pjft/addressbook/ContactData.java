package com.pjft.addressbook;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String avatar;
  private final String title;
  private final String companyInfo;
  private final String address;
  private final String mobilePhone;
  private final String email;
  private final String site;
  private final String year;

  public ContactData(String firstName, String lastName, String avatar, String title, String companyInfo, String address, String mobilePhone, String email, String site, String year) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.avatar = avatar;
    this.title = title;
    this.companyInfo = companyInfo;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.email = email;
    this.site = site;
    this.year = year;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAvatar() {
    return avatar;
  }

  public String getTitle() {
    return title;
  }

  public String getCompanyInfo() {
    return companyInfo;
  }

  public String getAddress() {
    return address;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getEmail() {
    return email;
  }

  public String getSite() {
    return site;
  }

  public String getYear() {
    return year;
  }
}
