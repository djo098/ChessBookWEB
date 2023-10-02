package persist;

/**
 * Interface class following a DataMapper pattern
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 * @param <E>
 */
public interface IDataMapper<E> {
	public E find(Class<E> e, int id);
	public void update(E e);
	public boolean remove(E e);
	public void insert(E e);
}
