/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.logica;

import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.pojo.Profesor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
            fos = new FileOutputStream("C:\\Users\\david\\Documents\\VIII Semestre\\archivo.txt");
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
            fis = new FileInputStream("C:\\Users\\david\\Documents\\VIII Semestre\\archivo.txt");
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
    public List<Profesor> retornarProfesores() {
        if (this.leer() != null) {
            this.ListaProfesores = this.leer();
            return this.ListaProfesores;
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
    public Profesor retornarProfesorCedula(String cedula) throws IOException, FileNotFoundException, ClassNotFoundException, Exception {
        if (cedula.length() < 11 && cedula.length() >= 7) {
            if (this.leer() != null) {
                System.out.println("entra al if");
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
                    return this.profesor;
                } else {
                    return null;
                }
            } else {
                throw new ObjectNotFoundException("El fichero no contiene datos");
            }
        } else {
            throw new Exception("Cedula invalida");
        }

    }

    /**
     * Metodo para eliminar un profesor registrado
     *
     * @param id variable para saber que profesor eliminar
     * @return int
     */
    public void eliminarProfesor(int id) throws IOException, FileNotFoundException, ClassNotFoundException {
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
            throw new FileNotFoundException("No existe el fichero");
        }

    }

    /**
     * metodo que edita a un profeso
     *
     * @param profesor
     * @return boolean que valida si la edicion fue correcta
     */
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
