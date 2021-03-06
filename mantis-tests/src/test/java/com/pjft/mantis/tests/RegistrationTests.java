package com.pjft.mantis.tests;

import com.pjft.mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

  @BeforeMethod
  public void startMailService(){
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException {
    long now = System.currentTimeMillis();
    String user = "user"+now;
    String password = "password";
    String email = String.format("user%s@test.com", now);
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2,10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailService(){
    app.mail().stop();
  }

}
