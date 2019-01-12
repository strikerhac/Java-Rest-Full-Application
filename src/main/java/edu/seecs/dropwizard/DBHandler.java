package edu.seecs.dropwizard;

import java.util.Base64;
import java.util.regex.Pattern;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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

	public boolean authorize(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		String split[] = decodedString.split(Pattern.quote("|"));
		String username = (split[0]);
		String password = (split[1]);
		init();
		String hql = "FROM Person p WHERE p.username=:uname and p.password=:pword";
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(hql);
		query.setParameter("uname", username);
		query.setParameter("pword", password);
		Person person = (Person) query.uniqueResult();
		close();
		if (person != null)
			return true;
		else
			return false;
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
