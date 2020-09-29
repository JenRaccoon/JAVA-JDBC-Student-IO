package java0709;

import java.util.List;

public interface IDAO<T> {
	boolean insert(T t);
	boolean update(T t);
	boolean delete(T t);
	T get(String id);
	T get(int id);
	List<T> getAll();
}
