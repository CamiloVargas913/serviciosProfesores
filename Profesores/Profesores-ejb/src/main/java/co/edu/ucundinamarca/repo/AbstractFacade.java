/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.repo;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author PROFESIONAL
 */
public abstract class AbstractFacade<T, V> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<T> listar(String consulta, int page,int size) {
        Query query =getEntityManager().createNamedQuery(consulta, entityClass);
        return query.setFirstResult(page).setMaxResults(size).getResultList();
    }

    public T listarPorId(V id) {
        return getEntityManager().find(entityClass, id);
    }

    public void guardar(T entity) {
        getEntityManager().persist(entity);
    }

    public void editar(T entity) {
        getEntityManager().merge(entity);
    }

    public void eliminar(T entity) {
        getEntityManager().remove(entity);
    }
}
