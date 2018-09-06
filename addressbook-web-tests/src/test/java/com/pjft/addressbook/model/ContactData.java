package com.pjft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
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
  private String group;
  private boolean creation;

  public ContactData(String firstName, String lastName, String avatar, String title, String companyInfo,
                     String address, String mobilePhone, String email, String site, String year, String group, boolean creation) {
    this.id = 0;
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
    this.group = group;
    this.creation = creation;
  }

  public ContactData(int id ,String firstName, String lastName, String avatar, String title, String companyInfo,
                     String address, String mobilePhone, String email, String site, String year, String group, boolean creation) {
    this.id = id;
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
    this.group = group;
    this.creation = creation;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
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

  public String getGroup() {
    return group;
  }

  public boolean isCreation() {
    return creation;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }
}
