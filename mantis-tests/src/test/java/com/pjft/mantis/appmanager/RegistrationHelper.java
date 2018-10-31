package com.pjft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String user, String email) {
    wd.get(app.getProperty("web.BaseURL") + "/index.php");
    type(By.name("username"), user);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }
}
