/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.logica;

import co.edu.ucundinamarca.pojo.Profesor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.ws.rs.core.Response;

/**
 *
 * @author PROFESIONAL
 */
public class ProfesorService {
    List<Profesor> ListaProfesores;
    public void registroProfesor(Profesor profesor){
        this.ListaProfesores.add(profesor);
        this.llenarProfesor();
    }
    
    public Response llenarProfesor() {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("bd.txt");
            ObjectOutputStream db = new ObjectOutputStream(fos);
            db.writeObject(this.ListaProfesores);
            db.flush();
            db.close();
            return Response.status(Response.Status.CREATED).entity("Insercion correcta").build();
        } catch (IOException ex) {
           return Response.status(Response.Status.BAD_REQUEST).entity("Error al insertar").build();
        }
    }
}
