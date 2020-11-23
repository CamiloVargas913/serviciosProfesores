/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.repo.impl;

import co.edu.ucundinamarca.repo.ILibroRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import co.edu.ucundinamarca.entity.Libro;
import co.edu.ucundinamarca.repo.AbstractFacade;
import static java.lang.Integer.parseInt;
import javax.persistence.Query;

/**
 *
 * @author david
 */
@Stateless
public class LibroRepoImpl extends AbstractFacade<Libro,Integer> implements ILibroRepo{
    
    @PersistenceContext(unitName = "co.edu.ucundinamarca_Profesores-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    public LibroRepoImpl() {
        super(Libro.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entity;
    }

    @Override
    public Integer totalRegistros() {
        Query query = this.entity.createNamedQuery("Libro.obtenerTotal");
        String data = query.getSingleResult().toString();
        return parseInt(data);
    }


    
}
