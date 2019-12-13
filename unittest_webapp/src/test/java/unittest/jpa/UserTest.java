package unittest.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
	@BeforeEach
	public void setup() throws Throwable{
		EntityManagerFactory.initialize("webapp");
	}

	@AfterEach
	public void tearDown() throws Throwable{
		EntityManagerFactory.destroy();
	}

	@Test
	public void test_findById() throws Throwable{
		long id = -1;
		String username = "user1";
		String dispname = "user1disp";
		String password = "pass";
		{	EntityManager em = EntityManagerFactory.create();
			em.getTransaction().begin();
			User u = new User(username, dispname, password);
			em.persist(u);
			id = u.getId();
			em.getTransaction().commit();
			em.close();
		}
		{	EntityManager em = EntityManagerFactory.create();
			User u = em.find(User.class, id);
			assertEquals(username, u.getUsername());
			assertEquals(dispname, u.getDisplayName());
			assertEquals(password, u.getPassword());
			em.close();
		}
		
	}

	@Test
	public void test_selectByUsername() throws Throwable{
		String username = "user1";
		String dispname = "user1disp";
		String password = "pass";
		{	EntityManager em = EntityManagerFactory.create();
			em.getTransaction().begin();
			User u = new User(username, dispname, password);
			em.persist(u);
			em.getTransaction().commit();
			em.close();
		}
		{	EntityManager em = EntityManagerFactory.create();
			User u = em.createQuery(
					"select u from User u where u.username=:username", User.class)
					.setParameter("username", username)
					.getSingleResult();
			assertEquals(username, u.getUsername());
			assertEquals(dispname, u.getDisplayName());
			assertEquals(password, u.getPassword());
			em.close();
		}
	}
}
