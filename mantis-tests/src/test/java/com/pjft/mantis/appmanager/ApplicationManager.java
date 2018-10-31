package com.pjft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver wd;
  WebDriverWait wait;

  private RegistrationHelper registrationHelper;
  private String browser;
  private MailHelper mailHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void stop() {
    try{
      Thread.sleep(4000);
    }catch (InterruptedException e){
      e.printStackTrace();
    }
    if (wd != null) {
      wd.quit();
      wd = null;
    }
  }

  public HttpSession newSession(){
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public WebDriver getDriver(){
    if (wd==null){
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
      wd.get(properties.getProperty("web.BaseURL"));
    }
    return wd;
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null){
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public MailHelper mail(){
    if (mailHelper == null){
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }
}
