/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service.impl;

import co.edu.ucundinamarca.entity.Profesor;
import co.edu.ucundinamarca.repo.IProfesorRepo;
import co.edu.ucundinamarca.service.IProfesorServiceEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author PROFESIONAL
 */
@Stateless
public class ProfesorServiceImplEntity implements IProfesorServiceEntity {

    @EJB
    private IProfesorRepo repo;

    @Override
    public List<Profesor> listar() {
        return this.repo.listar();
    }

    @Override
    public void insertar(Profesor profesor) {
        this.repo.insertar(profesor);
    }

    @Override
    public Profesor listarID(int id) {
        return this.repo.listarID(id);
    }

    @Override
    public void modificar(Profesor profesor) {
        this.repo.modificar(profesor);
    }
    
    @Override
    public void eliminar(Profesor profesor){
        this.repo.eliminar(profesor);
    }
}
