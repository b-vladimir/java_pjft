package com.pjft.mantis.appmanager;

import com.pjft.mantis.model.UserData;
import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String user, String email) {
    wd.get(app.getProperty("web.BaseURL") + "/signup_page.php");
    type(By.name("username"), user);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

  public void changePasswordByAdmin(UserData user) {
    wd.get(app.getProperty("web.BaseURL") + "/login.php");
    type(By.name("username"), app.getProperty("web.adminLogin"));
    type(By.name("password"), app.getProperty("web.adminPassword"));
    click(By.cssSelector("input[value='Login']"));
    click(By.xpath("//a[(text()='Manage')]"));
    click(By.xpath("//a[(text()='Manage Users')]"));
    click(By.xpath(String.format("//a[(text()='%s')]", user.getUsername())));
    click(By.cssSelector("input[value='Reset Password']"));
  }
}
