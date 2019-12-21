package com.example.sfgpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    Set<T> findAll() {
        // it wil return vack a new HashSet of the values
        return new HashSet<>(map.values());
    }

    // return back the object with that id
    T findById(ID id) {
        return map.get(id);
    }

    T save(ID id, T object) {
        map.put(id, object);
        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    // for that to work, we need to implement proper equals method for every entity
    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }
}
