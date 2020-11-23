/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.dto;

import co.edu.ucundinamarca.entity.Autor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PROFESIONAL
 */
public abstract class ListarPaginadoDto<A> {
    
    private List<A> content = new ArrayList<>();
    private int totalRegistros;

    public List<A> getContent() {
        return content;
    }

    public void setContent(List<A> entityClass) {
        this.content = entityClass;
    }
    
    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }
    
    
    
}
