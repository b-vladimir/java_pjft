package com.pjft.addressbook.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;
  WebDriverWait wait;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.CHROME)){
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    }else if (browser.equals(BrowserType.IE)){
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wd.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(wd, 30);
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login("admin", "secret");
    sessionHelper.initAut();
  }

  public void stop() {
    try{
      Thread.sleep(4000);
    }catch (InterruptedException e){
      e.printStackTrace();
    }
    wd.quit();
    wd = null;
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
