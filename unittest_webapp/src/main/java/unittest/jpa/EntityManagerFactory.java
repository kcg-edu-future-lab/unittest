package unittest.jpa;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {
	private static javax.persistence.EntityManagerFactory f;

	public static EntityManager create() {
		if(f == null) throw new IllegalStateException("EntityManagerFactory is not initizlied.");
		return f.createEntityManager();
	}

	public static synchronized void initialize(String unitName) {
		if(f != null) throw new IllegalStateException("EntityManagerFactory is already initialized.");
		f = Persistence.createEntityManagerFactory(unitName);
	}

	public static void destroy() {
		f.close();
		f = null;
	}
}
