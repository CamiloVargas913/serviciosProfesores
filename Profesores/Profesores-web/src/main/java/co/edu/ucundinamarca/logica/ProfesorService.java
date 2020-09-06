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
    public void registroProfesor(Profesor profesor){
        if(this.leer() != null){
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
            fos = new FileOutputStream("E:\\archivo.txt");
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
            fis = new FileInputStream("E:\\archivo.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (List<Profesor>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }
}
