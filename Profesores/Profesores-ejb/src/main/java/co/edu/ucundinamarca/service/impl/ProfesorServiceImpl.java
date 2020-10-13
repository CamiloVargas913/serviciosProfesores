/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.service.impl;

import co.edu.ucundinamarca.dto.Materia;
import co.edu.ucundinamarca.dto.Profesor;
import co.edu.ucundinamarca.dto.ProfesorBD;
import co.edu.ucundinamarca.exception.ListaVaciaException;
import co.edu.ucundinamarca.exception.ObjectNotFoundException;
import co.edu.ucundinamarca.service.IProfesorService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author PROFESIONAL
 */
@Stateless
public class ProfesorServiceImpl extends DatosImpl implements IProfesorService {

    /**
     * Lista de materias
     */
    private List<Materia> listaMaterias;

    private List<Profesor> listaProfesores;
    /**
     * listado de los profesores de tipo profesor
     */
    List<Profesor> ListaProfesores = new ArrayList();
    /**
     * variable profesor de tipo profesor
     */
    Profesor profesor = new Profesor();
    private int idProfesor;
    /**
     * boolean estado del retorno de la consulta sql
     */
    private boolean estado = true;
    private List<ProfesorBD> listaProfesor;

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
     * Metodo que retorna la lista de las materias de acuerdo al profesor
     *
     * @throws SQLException
     */
    @Override
    public void listaMateriasProfesor() throws SQLException {

        listaMaterias = new ArrayList<>();
        java.sql.Statement st = conexion.createStatement();
        try {
            String sql2 = "SELECT materia.id_materia,materia.nombre_materia,materia.cupos_materia,materia.creditos_materia FROM materia,profesor_materia where materia.id_materia=profesor_materia.id_materia and profesor_materia.id_profesor=" + this.idProfesor + ";";
            ResultSet result2 = st.executeQuery(sql2);
            while (result2.next()) {
                int idMateria = Integer.parseInt(result2.getString("id_materia"));
                int cuposMateria = Integer.parseInt(result2.getString("cupos_materia"));
                int creditosMateria = Integer.parseInt(result2.getString("creditos_materia"));
                listaMaterias.add(new Materia(idMateria, result2.getString("nombre_materia"), cuposMateria, creditosMateria));
            }
        } catch (SQLException | NumberFormatException e) {

        }
    }

    /**
     * Metodo que retorna la lista de todos los profesores registrados
     */
    @Override
    public void listarProfesor() {
        try {
            listaProfesor = new ArrayList<>();
            java.sql.Statement st = conexion.createStatement();
            String sql = "SELECT cedula_profesor, nombre_profesor, apellido_profesor, correo_profesor, edad_correo, id_profesor FROM public.profesor;";
            ResultSet result = st.executeQuery(sql);
            if (result.next() == false) {
                throw new ListaVaciaException("No hay datos registrados");
            } else {
                do {
                    this.idProfesor = Integer.parseInt(result.getString("id_profesor"));
                    int edad = Integer.parseInt(result.getString("edad_correo"));
                    int cedula = Integer.parseInt(result.getString("cedula_profesor"));
                    listaMateriasProfesor();
                    listaProfesor.add(new ProfesorBD(this.idProfesor, edad, cedula, result.getString("nombre_profesor"), result.getString("apellido_profesor"), result.getString("correo_profesor"), this.getListaMaterias()));
                } while (result.next());
            }
        } catch (SQLException ex) {

        }
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
    public List<Profesor> retornarProfesores() throws ObjectNotFoundException {
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
     * @throws co.edu.ucundinamarca.exception.ObjectNotFoundException
     */
    @Override
    public Profesor retornarProfesorCedula(String cedula) throws ObjectNotFoundException {
        System.out.println("logica" + cedula);
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
                } else {
                    throw new ObjectNotFoundException("Profesor no encontrado");
                }
            } else {
                return null;
            }
        } else {
            throw new ObjectNotFoundException("El fichero no contiene datos");
        }

    }

    /**
     * Metodo para eliminar un profesor registrado
     *
     * @param id variable para saber que profesor eliminar
     * @return int
     */
    @Override
    public void eliminarProfesor(int id) throws ObjectNotFoundException {
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
    public void editarProfesor(Profesor profesor) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectNotFoundException {
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
     * @throws co.edu.ucundinamarca.exception.ObjectNotFoundException
     */
    @Override
    public List<Profesor> retornarProfesorMateria(String materia) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectNotFoundException {
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

    @Override
    public void insertarProfesor(ProfesorBD profesorInsertar) throws Exception {

        String cadenaSql = " INSERT INTO public.profesor(cedula_profesor, nombre_profesor, apellido_profesor, correo_profesor, edad_correo)"
                + "VALUES (" + Integer.parseInt(profesorInsertar.getCedula().toString()) + ",'"
                + profesorInsertar.getNombre() + "','" + profesorInsertar.getApellido() + "','" + profesorInsertar.getCorreo() + "'," + Integer.parseInt(profesorInsertar.getEdad().toString()) + ");";
        modifacionBaseDatos(cadenaSql);
        traerUltimoID();
        insertarTablaAsociativa(profesorInsertar.getListaMateria(), this.idProfesor);

    }

    @Override
    public void traerUltimoID() throws SQLException {
        java.sql.Statement st = conexion.createStatement();
        String sql = "SELECT MAX(id_profesor) as id_profesor FROM public.profesor;";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            this.idProfesor = Integer.parseInt(result.getString("id_profesor"));
        }
        System.out.println(this.idProfesor);
    }

    @Override
    public void traerCedula(int cedula) {
        try {
            listaProfesores = new ArrayList<>();
            java.sql.Statement st = conexion.createStatement();
            String sql = "SELECT id_profesor, cedula_profesor, nombre_profesor, apellido_profesor, correo_profesor, edad_correo FROM public.profesor where cedula_profesor=" + cedula + ";";
            ResultSet result = st.executeQuery(sql);
            if (result.next() == false) {
                this.estado = false;
            } else {
                do {
                    this.idProfesor = Integer.parseInt(result.getString("id_profesor"));
                    int edad = Integer.parseInt(result.getString("edad_correo"));
                    int numeroCedula = Integer.parseInt(result.getString("cedula_profesor"));
                    listaMateriasProfesor();
                    listaProfesor.add(new ProfesorBD(this.idProfesor, edad, numeroCedula, result.getString("nombre_profesor"), result.getString("apellido_profesor"), result.getString("correo_profesor"), this.getListaMaterias()));
                } while (result.next());
            }
        } catch (SQLException ex) {

        }
    }

    public void insertarTablaAsociativa(List<Materia> listaMateria, int idProfesor) {
        for (Materia materia : listaMateria) {
            String cadenaSql = " INSERT INTO public.profesor_materia(id_profesor, id_materia)"
                    + "VALUES (" + idProfesor + "," + Integer.parseInt(materia.getId().toString()) + ");";
            modifacionBaseDatos(cadenaSql);
        }
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    @Override
    public List<ProfesorBD> getListaProfesor() {
        return listaProfesor;
    }

    public void setListaProfesor(List<ProfesorBD> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }

    public List<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(List<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
