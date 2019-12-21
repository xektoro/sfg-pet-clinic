package com.example.sfgpetclinic.services;

import java.util.Set;

// here, we are using Java Generics
// it will mimic the Spring Data CRUD Repository
// so now all our service interfaces are going to inherit these methods
// and they can add also additional methods
public interface CrudService<T, ID> {
    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
