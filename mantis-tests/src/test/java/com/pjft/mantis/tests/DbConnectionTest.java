package com.pjft.mantis.tests;

import com.pjft.mantis.model.UserData;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DbConnectionTest {

  @Test
  public void testBdConnection(){
    Connection conn = null;

    try {
      conn =
              DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?" +
                      "user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select id, username, email  from mantis_user_table");
      Set<UserData> users = new HashSet<>();
      while (rs.next()){
        users.add(new UserData().withId(rs.getInt("id")).
                withUsername(rs.getString("username")).withEmail(rs.getString("email")));
      }
      rs.close();
      st.close();
      conn.close();

      System.out.println(users.size());
      System.out.println(users);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
