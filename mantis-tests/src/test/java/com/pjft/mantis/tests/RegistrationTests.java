package com.pjft.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

  @Test
  public void testRegistration(){
    app.registration().start("user1", "user1@test.com");
  }
}
