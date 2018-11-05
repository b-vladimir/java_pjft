package com.pjft.mantis.tests;

import com.pjft.mantis.model.MailMessage;
import com.pjft.mantis.model.UserData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

  @BeforeMethod
  public void startMailService(){
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException {
    UserData user = app.db().users().iterator().next();
    String password = "password";
    String email = user.getEmail();
    app.registration().changePasswordByAdmin(user);
    List<MailMessage> mailMessages = app.mail().waitForMail(1,20000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user.getUsername(), password));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailService(){
    app.mail().stop();
  }
}
