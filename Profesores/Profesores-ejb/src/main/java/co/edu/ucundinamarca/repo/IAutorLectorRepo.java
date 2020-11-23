/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.repo;

import co.edu.ucundinamarca.entity.AutorLector;
import java.util.List;

/**
 *
 * @author PROFESIONAL
 */
public interface IAutorLectorRepo {

    public void guardar(AutorLector autorLector);

    public List<AutorLector> listarAutorLector(Integer idAutor);
    
    public int desasociarLector(int idAutor, int idLector);
}
