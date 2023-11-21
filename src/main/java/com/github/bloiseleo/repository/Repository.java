package com.github.bloiseleo.repository;

import java.util.Collection;

public interface Repository<T, X> {
    public void save(T dto);
    X getLastIdInserted();
    Collection<T> listAll();
    void deleteById(X x);
    T findById(X id);
    void update(X id, T data);
}
