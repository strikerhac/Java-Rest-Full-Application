package edu.seecs.dropwizard;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBHandler {
	private Configuration config;
	private SessionFactory factory;
	private Session session;
	private Transaction t;

	public DBHandler() {
		config = new Configuration();
		config.addAnnotatedClass(Person.class);
		config.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
				.build();
		this.factory = config.buildSessionFactory(serviceRegistry);
	}

	public void save(Person p) {
		init();
		session.save(p);
		close();
	}

	public void delete(int id) {
		init();
		Person person = session.get(Person.class, id);
		session.delete(person);
		close();
	}

	public void update(Person p) {
		init();
		session.update(p);
		close();
	}

	public Person get(int id) {
		init();
		Person person = session.get(Person.class, id);
		close();
		return person;
	}

	public void init() {
		this.session = factory.openSession();
		this.t = session.beginTransaction();
	}

	public void close() {
		t.commit();
		session.close();
	}
}
