/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.repo.impl;

import co.edu.ucundinamarca.entity.Profesor;
import co.edu.ucundinamarca.repo.AbstractFacade;
import co.edu.ucundinamarca.repo.IProfesorRepo;
import static java.lang.Integer.parseInt;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PROFESIONAL
 */
@Stateless
public class ProfesorRepo extends AbstractFacade<Profesor, Integer> implements IProfesorRepo {

    @PersistenceContext(unitName = "co.edu.ucundinamarca_Profesores-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    public ProfesorRepo() {
        super(Profesor.class);
    }

    @Override
    public Integer validarCedula(String cedula, Integer id) {
        Query query = this.entity.createNamedQuery("Profesor.validarCedula");
        query.setParameter("cedula", cedula);
        query.setParameter("id", id);
        String data = query.getSingleResult().toString();
        return parseInt(data);
    }

    @Override
    public Integer validarCorreo(String correo, Integer id) {
        Query query = this.entity.createNamedQuery("Profesor.validarCorreo");
        query.setParameter("correo", correo);
        query.setParameter("id", id);
        String data = query.getSingleResult().toString();
        return parseInt(data);
    }

    @Override
    public Integer validarCedulaInsert(String cedula) {
        Query query = this.entity.createNamedQuery("Profesor.validarCedulaInsert");
        query.setParameter("cedula", cedula);
        String data = query.getSingleResult().toString();
        return parseInt(data);
    }

    @Override
    public Integer validarCorreoInsert(String correo) {
        Query query = this.entity.createNamedQuery("Profesor.validarCorreoInsert");
        query.setParameter("correo", correo);
        String data = query.getSingleResult().toString();
        return parseInt(data);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entity;
    }

    @Override
    public Integer totalRegistros() {
        Query query = this.entity.createNamedQuery("Profesor.obtenerTotal");
        String data = query.getSingleResult().toString();
        return parseInt(data);
    }

}
