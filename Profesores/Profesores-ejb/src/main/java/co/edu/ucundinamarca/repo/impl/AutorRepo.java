/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.repo.impl;

import co.edu.ucundinamarca.entity.Autor;
import co.edu.ucundinamarca.entity.Lector;
import co.edu.ucundinamarca.entity.ViewAutor;
import co.edu.ucundinamarca.repo.AbstractFacade;
import co.edu.ucundinamarca.repo.IAutorRepo;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author david
 */
@Stateless
public class AutorRepo extends AbstractFacade<Autor, Integer> implements IAutorRepo {

    @PersistenceContext(unitName = "co.edu.ucundinamarca_Profesores-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    public AutorRepo() {
        super(Autor.class);
    }

    @Override
    public Integer validarExisteAutor(Integer id) {
        Query query = this.entity.createNamedQuery("Autor.validarAutorId");
        query.setParameter("id", id);
        String data = query.getSingleResult().toString();
        return parseInt(data);

    }

    @Override
    public void cambiarEstado(Integer id, boolean estado) {
        Query query = this.entity.createNamedQuery("Autor.cambiarEstado");
        query.setParameter("estado", estado);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entity;
    }

    @Override
    public List<ViewAutor> listarVista() {
        TypedQuery<ViewAutor> listaAutor = this.entity.createNamedQuery("ViewAutor.listarView", ViewAutor.class);
        return listaAutor.getResultList();
    }

    @Override
    public ViewAutor listarVistaId(Integer id) {
        ViewAutor listaAutor = this.entity.find(ViewAutor.class, id);
        return listaAutor;
    }

    @Override
    public Integer totalRegistros() {
        Query query = this.entity.createNamedQuery("Autor.obtenerTotal");
        String data = query.getSingleResult().toString();
        return parseInt(data);
    }

    @Override
    public List<Lector> listarLector(String consulta) {
        Query query = getEntityManager().createNamedQuery(consulta, Lector.class);
        return query.getResultList();
    }
    @Override
    public Lector listarLectorId(int id) {
        Lector lector = this.entity.find(Lector.class, id);
        return lector;
    }

}
