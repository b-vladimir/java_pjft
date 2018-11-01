package com.pjft.mantis.appmanager;

import com.pjft.mantis.model.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Set<UserData> groups(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery( "from UserData" ).list();
    session.getTransaction().commit();
    session.close();
    return new HashSet<>(result);
  }
}
