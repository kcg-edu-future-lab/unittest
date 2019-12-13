package unittest.jpa;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserTest {
	@BeforeAll
	public static void setup() throws Throwable{
		EntityManagerFactory.initialize("localtest");
	}

	@Test
	public void test() throws Throwable{
		EntityManager em = EntityManagerFactory.create();
		em.getTransaction().begin();
		em.persist(new User("user1", "pass", "user1"));
		em.getTransaction().commit();
		em.close();
	}
}
