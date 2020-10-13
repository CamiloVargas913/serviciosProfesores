/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service.impl;

import co.edu.ucundinamarca.entity.Profesor;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.exception.ParamRequiredException;
import co.edu.ucundinamarca.exception.ParamUsedException;
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
    public void insertar(Profesor profesor) throws ParamUsedException{
        this.validarCamposInsert(profesor);
        this.repo.insertar(profesor);
    }

    @Override
    public Profesor listarID(Integer id) throws ObjectNotFoundException{
        Profesor profesor = repo.listarID(id);
        if(profesor != null) 
            return profesor;
        else
            throw new ObjectNotFoundException("Profesor no existe.");
    }

    @Override
    public void modificar(Profesor profesor) throws ParamRequiredException, ObjectNotFoundException, ParamUsedException{
        if(profesor.getId() == null)    
            throw new ParamRequiredException("Id es requerido para edición");
        else {
            this.listarID(profesor.getId()); 
            this.validarCamposEdicion(profesor);
            repo.modificar(profesor);
        }
    }
    
    @Override
    public void eliminar(Integer id) throws ObjectNotFoundException{
        Profesor profesor = this.listarID(id);
        this.repo.eliminar(profesor);
    }
    
    private void validarCamposEdicion(Profesor profesor) throws ParamUsedException{
            Integer validacion = repo.validarCedula(profesor.getCedula(), profesor.getId());
            if(validacion != 0)
                throw new ParamUsedException("Cédula ya se encuentra registrada");
    
            validacion = repo.validarCorreo(profesor.getCorreo(), profesor.getId());
            if(validacion != 0)
                throw new ParamUsedException("Correo ya se encuentra registradao");            
    }
    
    private void validarCamposInsert(Profesor profesor) throws ParamUsedException{
            Integer validacion = repo.validarCedulaInsert(profesor.getCedula());
            if(validacion != 0)
                throw new ParamUsedException("Cédula ya se encuentra registrada");
    
            validacion = repo.validarCorreoInsert(profesor.getCorreo());
            if(validacion != 0)
                throw new ParamUsedException("Correo ya se encuentra registradao");            
    }
}
