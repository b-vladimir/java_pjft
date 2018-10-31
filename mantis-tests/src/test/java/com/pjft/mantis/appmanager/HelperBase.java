package com.pjft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
  protected ApplicationManager app;
  protected WebDriver wd;


  public HelperBase(ApplicationManager app) {
    this.app = app;
    wd= app.getDriver();
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void selectCheckBox(By locator) {
    if (!wd.findElement(locator).isSelected()) {
      wd.findElement(locator).click();
    }
  }

  protected void type(By locator, String text) {
    click(locator);
    if(text!=null){
      String existingText = wd.findElement(locator).getAttribute("value");
      if (! existingText.equals(text)){
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void acceptDialogWindow() {
    wd.switchTo().alert().accept();
  }

  public boolean isElementPresent(By locator) {
    try{
      wd.findElement(locator);
      return true;
    }catch (NoSuchElementException ex) {
      return false;
    }
  }
}