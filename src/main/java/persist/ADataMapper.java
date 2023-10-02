package persist;

import javax.persistence.EntityManager;

import main.Main;

/**
 * Abstract class following a DataMapper pattern
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 * @param <E> A class instance
 */
public abstract class ADataMapper<E> implements IDataMapper<E> {
	private Class<E> e;

	/**
	 * Class constructor
	 * 
	 * @param e Object to persist
	 */
	public ADataMapper(Class<E> e) {
		this.setE(e);
	}

	public E find(Class<E> e, int id) {
		EntityManager em = null;
		E t = null;
		try {
			em = Main.emf.createEntityManager();
			t = em.find(e, id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return t;
	}

	public void update(E e) {
		EntityManager em = null;
		try {
			em = Main.emf.createEntityManager();
			em.getTransaction().begin();
			if (!em.contains(e)) {
				em.merge(e);
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public boolean remove(E e) {
		EntityManager em = null;
		boolean b = false;
		try {
			em = Main.emf.createEntityManager();
			em.getTransaction().begin();
			if (!em.contains(e)) {
				e = em.merge(e);
			}
			em.remove(e);
			em.getTransaction().commit();
			b = true;
		} catch (Exception exception) {
			System.err.println(exception);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return b;
	}

	public void insert(E e) {
		EntityManager em = null;
		try {
			em = Main.emf.createEntityManager();
			em.getTransaction().begin();
			if (!em.contains(e)) {
				em.merge(e);
			}
			em.persist(e);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public Class<E> getE() {
		return e;
	}

	public void setE(Class<E> e) {
		this.e = e;
	}
}
