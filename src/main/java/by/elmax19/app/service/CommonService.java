package by.elmax19.app.service;

public interface CommonService<T, K> {
    T findById(K id);
}
