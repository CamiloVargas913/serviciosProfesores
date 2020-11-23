/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.repo.impl;

import co.edu.ucundinamarca.entity.AutorLector;
import co.edu.ucundinamarca.repo.IAutorLectorRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author PROFESIONAL
 */
@Stateless
public class AutorLectorRepoImpl implements IAutorLectorRepo {

    @PersistenceContext(unitName = "co.edu.ucundinamarca_Profesores-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    @Override
    public void guardar(AutorLector autorLector) {
        entity.persist(autorLector);
    }

    @Override
    public List<AutorLector> listarAutorLector(Integer idAutor) {
        this.entity.getEntityManagerFactory().getCache().evictAll();
        TypedQuery<AutorLector> listaAutorLector = this.entity.createNamedQuery("AutorLector.listarLectorPorAutor", AutorLector.class);
        listaAutorLector.setParameter("idAutor", idAutor);
        return listaAutorLector.getResultList();
    }

    
    @Override
    public int desasociarLector(int idAutor, int idLector) {
        Query autorLector = this.entity.createNamedQuery("AutorLector.desasociar", AutorLector.class);
        autorLector.setParameter("idAutor", idAutor);
        autorLector.setParameter("idLector", idLector);            
       return  autorLector.executeUpdate();
    }

}
