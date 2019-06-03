package de.thb.mux.service.service_api;

import java.util.Collection;

public interface CrudServiceApi <T, ID> {

    T create(T t);

    Collection<T> findAll();

    T findByID(ID id);

    T update(T t);

    Boolean deleteById(ID id);

    default Boolean exists(ID id) {
        return findByID(id) != null;
    }
}
