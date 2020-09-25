/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service.impl;

import co.edu.ucundinamarca.dto.Profesor;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.service.IProfesorService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author PROFESIONAL
 */
@Stateless
public class ProfesorServiceImpl implements IProfesorService{

    /**
     * listado de los profesores de tipo profesor
     */
    List<Profesor> ListaProfesores = new ArrayList();
    /**
     * variable profesor de tipo profesor
     */
    Profesor profesor = new Profesor();

    /**
     * metodo que recibe un objeto profesor y lo agrega a la lista de profesores
     *
     * @param profesor
     */
    @Override
    public void registroProfesor(Profesor profesor) {
        if (this.leer() != null) {
            this.ListaProfesores = this.leer();
        }
        this.ListaProfesores.add(profesor);
        this.llenarProfesor();
    }

    /**
     * metodo void que registra la lista de profesores en el fichero
     */
    @Override
    public void llenarProfesor() {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("E:\\archivo.txt");
            ObjectOutputStream db = new ObjectOutputStream(fos);
            db.writeObject(this.ListaProfesores);
            db.flush();
            db.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * metodo que retorna la lista de los profesores registrados en el fichero
     *
     * @return lista Profesor
     */
    @Override
    public List<Profesor> leer() {
        FileInputStream fis;
        try {
            fis = new FileInputStream("E:\\archivo.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (List<Profesor>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    /**
     * Metodo para retornar todos los profesores registrados
     *
     * @return List
     */
    @Override
    public List<Profesor> retornarProfesores() {
        if (this.leer() != null) {
            this.ListaProfesores = this.leer();
            if (this.ListaProfesores.isEmpty()) {
                throw new ObjectNotFoundException("No hay registros");
            } else {
                return this.ListaProfesores;
            }
        } else {
            throw new ObjectNotFoundException("No hay registros");
        }
    }

    /**
     * Retorna un profesor dependiendo la cedula del mismo
     *
     * @param cedula parametro para filtrar un profesor
     * @return Profesor
     */
    @Override
    public Profesor retornarProfesorCedula(String cedula) throws  ObjectNotFoundException{
        if (cedula.length() < 11 && cedula.length() >= 7) {
            if (this.leer() != null) {
                boolean bandera = true;
                this.ListaProfesores = this.leer();
                for (Profesor ListaProfesore : ListaProfesores) {
                    if (ListaProfesore.getCedula().equals(cedula)) {
                        this.profesor = ListaProfesore;
                        return this.profesor;
                    } else {
                        bandera = false;
                        throw new ObjectNotFoundException("Estudiante no encontrado");
                    }
                }
                if (bandera) {
                    if (this.profesor.getId() != 0) {
                        return this.profesor;
                    }else{
                         throw new ObjectNotFoundException("Profesor no encontrado");
                    }
                } else {
                    return null;
                }
            } else {
                throw new ObjectNotFoundException("El fichero no contiene datos");
            }
        } else {
            throw new ObjectNotFoundException("Cedula invalida");
        }

    }

    /**
     * Metodo para eliminar un profesor registrado
     *
     * @param id variable para saber que profesor eliminar
     * @return int
     */
    @Override
    public void eliminarProfesor(int id) throws ObjectNotFoundException{
        if (this.leer() != null) {
            this.ListaProfesores = this.leer();
            boolean bandera = false;
            for (Profesor ListaProfesore : ListaProfesores) {
                if (ListaProfesore.getId() == id) {
                    this.ListaProfesores.remove(ListaProfesore);
                    bandera = true;
                    break;
                }
            }
            if (bandera) {
                this.llenarProfesor();
            } else {
                throw new ObjectNotFoundException("No existe el id");
            }
        } else {
            throw new ObjectNotFoundException("No existe el fichero");
        }

    }

    /**
     * metodo que edita a un profeso
     *
     * @param profesor
     * @return boolean que valida si la edicion fue correcta
     */
    @Override
    public void editarProfesor(Profesor profesor) throws IOException, FileNotFoundException, ClassNotFoundException {
        this.eliminarProfesor(profesor.getId());
        this.registroProfesor(profesor);
        this.llenarProfesor();

    }

    /**
     * Metodo para retornar un profesor dependiendo la materia que dicta
     *
     * @param materia Variable para saber la materia por la cual se esta
     * filtrando
     * @return List
     */
    @Override
    public List<Profesor> retornarProfesorMateria(String materia) throws IOException, FileNotFoundException, ClassNotFoundException {
        List<Profesor> profesores = new ArrayList();
        if (this.leer() != null) {
            this.ListaProfesores = this.leer();
            for (Profesor ListaProfesor : ListaProfesores) {
                for (String listaMateria : ListaProfesor.getListaMateria()) {
                    if (listaMateria.contains(materia)) {
                        profesores.add(ListaProfesor);
                    }
                }
            }
        } else {
            throw new ObjectNotFoundException("No hay datos en el fichero");
        }
        if (profesores.size() <= 0) {
            throw new ObjectNotFoundException("No hay registros con esa materia");
        } else {
            return profesores;
        }
    }

    
}
