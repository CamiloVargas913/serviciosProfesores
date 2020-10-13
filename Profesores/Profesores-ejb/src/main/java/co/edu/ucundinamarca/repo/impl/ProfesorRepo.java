/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.repo.impl;

import co.edu.ucundinamarca.entity.Profesor;
import co.edu.ucundinamarca.repo.IProfesorRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PROFESIONAL
 */
@Stateless
public class ProfesorRepo implements IProfesorRepo {

    @PersistenceContext(unitName = "co.edu.ucundinamarca_Profesores-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    @Override
    public List<Profesor> listar() {
        List listprofesor = this.entity.createQuery("SELECT c FROM Profesor c").getResultList();
        return listprofesor;
    }

    @Override
    public void insertar(Profesor profesor) {
        this.entity.persist(profesor);
    }

    @Override
    public Profesor listarID(int id) {
        return this.entity.find(Profesor.class, id);
    }
    @Override
    public void modificar(Profesor profesor){
        this.entity.merge(profesor);
    }
     @Override
    public void eliminar(Profesor profesor){
        this.entity.remove(profesor);
    }

}
