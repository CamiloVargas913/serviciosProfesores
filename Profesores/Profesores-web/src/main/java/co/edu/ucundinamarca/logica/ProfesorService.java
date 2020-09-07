/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.logica;

import co.edu.ucundinamarca.pojo.Profesor;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;

/**
 *
 * @author David Márquez
 */
public class ProfesorService {

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
     * @return List
     */
    
    public List<Profesor> retornarProfesores() {
        if (this.leer() != null) {
            this.ListaProfesores = this.leer();
            return this.ListaProfesores;
        } else {
            return null;
        }
    }

    /**
     * Retorna un profesor dependiendo la cedula del mismo
     * @param cedula parametro para filtrar un profesor 
     * @return Profesor
     */
    public Profesor retornarProfesorCedula(String cedula) {
        if (this.leer() != null) {
            boolean bandera = true;
            this.ListaProfesores = this.leer();
            for (Profesor ListaProfesore : ListaProfesores) {
                if (ListaProfesore.getCedula().equals(cedula)) {
                    this.profesor = ListaProfesore;
                    return this.profesor;
                } else {
                    bandera = false;
                }
            }
            if (bandera) {
                return this.profesor;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Metodo para eliminar un profesor registrado
     * @param id variable para saber que profesor eliminar
     * @return int
     */
    public int eliminarProfesor(int id) {
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
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }

    }

    /**
     * metodo que edita a un profeso
     * @param profesor 
     * @return boolean que valida si la edicion fue correcta 
     */
    public boolean editarProfesor(Profesor profesor) {
        int validacion = this.eliminarProfesor(profesor.getId());
        if (validacion == 1) {
            this.registroProfesor(profesor);
            this.llenarProfesor();
            return true;
        } else {
            return false;
        }

    }

    /**
     * Metodo para retornar un profesor dependiendo la materia que dicta
     * @param materia Variable para saber la materia por la cual se esta filtrando
     * @return List
     */
    public List<Profesor> retornarProfesorMateria(String materia) {
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
            return null;
        }
        if (profesores.size() <= 0) {
            return null;
        } else {
            return profesores;
        }
    }
}
