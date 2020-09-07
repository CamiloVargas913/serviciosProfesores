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
 * @author PROFESIONAL
 */
public class ProfesorService {

    List<Profesor> ListaProfesores = new ArrayList();
    Profesor profesor = new Profesor();

    public void registroProfesor(Profesor profesor) {
        if (this.leer() != null) {
            this.ListaProfesores = this.leer();
        }
        this.ListaProfesores.add(profesor);
        this.llenarProfesor();
        for (Profesor ListaProfesore : ListaProfesores) {
            System.out.println(ListaProfesore.getApellido());
        }
    }

    public void llenarProfesor() {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("C:\\Users\\david\\Documents\\VIII Semestre\\Linea II\\serviciosProfesores\\archivo.txt");
            ObjectOutputStream db = new ObjectOutputStream(fos);
            db.writeObject(this.ListaProfesores);
            db.flush();
            db.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public List<Profesor> leer() {
        FileInputStream fis;
        try {
            fis = new FileInputStream("C:\\Users\\david\\Documents\\VIII Semestre\\Linea II\\serviciosProfesores\\archivo.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (List<Profesor>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    public List<Profesor> retornarProfesores() {
        if (this.leer() != null) {
            this.ListaProfesores = this.leer();
            return this.ListaProfesores;
        } else {
            return null;
        }
    }

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

    public String eliminarProfesor(int id) {
        if (this.leer() != null) {
            this.ListaProfesores = this.leer();
            boolean bandera = true;
            Iterator<Profesor> iterator = this.ListaProfesores.iterator();
            while (iterator.hasNext()) {
                Profesor datos = iterator.next();
                if (id == datos.getId()) {
                    iterator.remove();
                    bandera = true;
                } else {
                    bandera = false;
                }
            }
            for (Profesor ListaProfe : ListaProfesores) {
                System.out.println(ListaProfe.getNombre());
            }
            if (bandera) {
                this.llenarProfesor();
                return "Eliminado correctamente";
            } else {
                return "El id no existe";
            }
        } else {
            return "No hay ningun profesor registrado";
        }

    }

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
